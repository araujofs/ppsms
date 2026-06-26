package main

import (
	"fmt"
	"log"
	"os"
	"os/exec"
	"os/signal"
	"strings"
	"syscall"
	"time"

	"github.com/fsnotify/fsnotify"
)

// ainda falta debounce de uns 3 segundos para [x]
// gerar imagem do diagrama e
// abrir (reabrir) imagem com visualizador padrao

const (
	pumlJarPath      = "/home/arthur/packages/plantuml.jar"
	outFormat        = "png"
	dirToWatch       = "./../"
	diagramWorkspace = 1
	realWorkspace    = 2
)

type Viewer struct {
	cmd  *exec.Cmd
	done chan error
}

func (v *Viewer) Start(diagramFilepath string) {
	if v.IsRunning() {
		log.Println("error: cmd already running")
		return
	}

	cmd := exec.Command("imv", "-f", diagramFilepath)
	v.cmd = cmd
	if err := v.cmd.Start(); err != nil {
		log.Fatal("error:  ", err)
	}

	done := make(chan error, 1)
	go func() {
		done <- v.cmd.Wait()
	}()
}

func (v *Viewer) IsRunning() bool {
	if v.cmd == nil || v.done == nil {
		return false
	}

	select {
	case <-v.done:
		v.cmd = nil
		v.done = nil
		return false
	default:
		return true
	}
}

func main() {
	viewer := &Viewer{}

	signalChannel := make(chan os.Signal, 2)
	signal.Notify(signalChannel, os.Interrupt, syscall.SIGINT)

	go handleQuit(viewer, signalChannel)

	watcher := createWatcher()
	defer watcher.Close()

	watchEvents(watcher, viewer)
}

func handleQuit(viewer *Viewer, sigChan <-chan os.Signal) {
	for range sigChan {
		if viewer.IsRunning() {
			viewer.cmd.Process.Kill()
		}

		os.Exit(0)
	}
}

func renderDiagram(textDiagramPath string) string {
	cmd := exec.Command("java", "-jar", pumlJarPath, "-f", outFormat, textDiagramPath)

	output, err := cmd.CombinedOutput()
	if err != nil {
		log.Printf("rendering error: %s\n", err)
		log.Printf("rendering error output: %s\n", output)
	}

	diagramPath := strings.Join([]string{strings.Split(textDiagramPath, ".puml")[0], outFormat}, ".")

	log.Printf("rendering: (%s)\n", textDiagramPath)
	log.Printf("rendering: diagram rendered (%s)\n", diagramPath)

	return diagramPath
}

func watchEvents(watcher *fsnotify.Watcher, viewer *Viewer) {
	var timer *time.Timer

	for {
		select {
		case event, ok := <-watcher.Events:
			if !ok {
				return
			}

			if event.Has(fsnotify.Write) && strings.Contains(event.Name, ".puml") {
				// debounce
				if timer != nil {
					timer.Stop()
				}

				timer = time.AfterFunc(2*time.Second, func() {
					focusWorkspace(diagramWorkspace)
					diagramPath := renderDiagram(event.Name)

					viewer.Start(diagramPath)
				})

				log.Println("event: ", event)
			}

		case err, ok := <-watcher.Errors:
			if !ok {
				return
			}

			log.Println("error: ", err)
		}
	}
}

func createWatcher() *fsnotify.Watcher {
	watcher, err := fsnotify.NewWatcher()
	if err != nil {
		log.Fatal(err)
	}

	err = watcher.Add(dirToWatch)
	if err != nil {
		log.Fatal(err)
	}

	return watcher
}

func openImv(diagramFilepath string) *exec.Cmd {
	cmd := exec.Command("imv", "-f", diagramFilepath)
	if err := cmd.Start(); err != nil {
		log.Fatal("error:  ", err)
	}

	return cmd
}

func focusWorkspace(workspace int) {
	cmd := exec.Command("hyprctl", "dispatch", "workspace", fmt.Sprint(workspace))
	if err := cmd.Run(); err != nil {
		log.Fatalln("error: ", err)
	}
}
