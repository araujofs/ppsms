# Sistema de Submissão e Avaliação de Artigos Científicos

> **Problema multi-pattern – Em Desenvolvimento**

| Campo | Informação |
|---|---|
| **Curso** | Sistemas para Internet |
| **Disciplina** | Padrões de Projeto de Software |
| **Período** | 5º |
| **Professor** | Alex Sandro da Cunha Rêgo |

---

## I – Introdução

Pesquisas científicas normalmente são submetidas a jornais ou eventos científicos (simpósios, congressos) para serem avaliadas e, em caso de aprovação, aceitas para publicação. O aceite é o reconhecimento da academia pelo rigor metodológico, contribuição da pesquisa e resultados obtidos.

Para gerenciar a submissão das propostas, um sistema faz-se necessário para gerenciar o fluxo completo de submissão e revisão de artigos científicos em um evento acadêmico. O sistema permitirá que autores submetam artigos científicos em formato PDF, enquanto revisores especializados avaliam os trabalhos conforme sua área de conhecimento.

O objetivo pedagógico do presente projeto é:

- Compreender regras de negócio reais;
- Praticar a modelagem de problemas do mundo real utilizando o paradigma orientado a objetos;
- Garantir a aplicação dos princípios SOLID;
- Utilizar padrões de projeto (Design Patterns) em um contexto acadêmico;
- Desenvolver uma arquitetura modular e extensível em Java.

---

## II – Domínio do Problema

Neste projeto de disciplina, a equipe vai desenvolver uma solução com gerenciamento de periódicos e eventos. Três tipos de usuários definem os papéis dentro do sistema:

- **Autor (pesquisador):** submete artigos, podendo também ser revisor;
- **Revisor:** pessoa de maior titulação acadêmica que avalia os artigos submetidos;
- **Coordenador:** convida os revisores (devem estar previamente cadastrados como "pesquisador") e define os temas em que os artigos devem se enquadrar (e.g., "IA aplicada a saúde", "Otimização de Algoritmos", "Aprendizado de Máquina Supervisionado", "Visão computacional", "LLMs"). Habilita o sistema para o recebimento de submissões e define a data limite para submissão.

Em linhas gerais, o sistema deve contemplar os seguintes requisitos:

- Cadastro de usuários (pesquisadores de interesse no sistema — pode ser um autor que vai submeter um trabalho ou um revisor que irá avaliá-lo);
- Cadastro de áreas de especialidade;
- Convite para revisores;
- Gerenciamento da submissão de artigos;
- Controle de prazos de submissão;
- Distribuição automática de artigos para os revisores;
- Avaliação do artigo (feedback do revisor);
- Aceite ou recusa da pesquisa por parte do revisor;
- Notificações por e-mail aos revisores escolhidos para avaliar artigos;
- Histórico de avaliações de artigos para cada revisor.

Para submeter artigos, o usuário precisa ter uma conta no sistema. Os revisores também devem se cadastrar como usuário comum — a diferença é que eles são convidados a fazerem parte do comitê técnico de avaliação de artigos.

> **Blind-review:** Os artigos passam por um protocolo de revisão por pares às cegas (*blind-review*). Nesse processo, os trabalhos submetidos são avaliados por especialistas na área de forma anônima, garantindo que a identidade dos autores e dos revisores seja mantida em segredo. Isso ajuda a garantir a imparcialidade e a qualidade da avaliação.

---

## III – Requisitos Funcionais

### RF01 – Start

Requisito que prepara o sistema para iniciar um novo evento de submissão. O sistema deve ficar pronto para receber revisores e submissões de trabalho até uma data específica. Cada vez que essa função for requisitada, os dados relativos ao evento anterior serão desconsiderados.

Cada novo evento é identificado por nome, cidade e período. Exemplo:

```
Simpósio Brasileiro de Sistemas de Informação (SBSI) - 2026
25 de maio a 28 de maio de 2026
Vitória - ES
```

---

### RF02 – Cadastro de Usuários

Permite que novos usuários se cadastrem no sistema informando e-mail (chave), senha e instituição.

---

### RF03 – Cadastro de Áreas Temáticas de Avaliação

As áreas temáticas são especificadas por palavras-chave utilizadas pelos autores para declarar em quais categorias seu artigo se enquadra (e.g., machine learning, LLMs, visão computacional, ciência de dados, informática na saúde). Mais de uma área temática pode ser escolhida. Essas áreas são cadastradas pelo coordenador do evento.

As mesmas áreas temáticas são informadas por cada revisor ao aceitar o convite para compor o comitê, de modo que o sistema possa direcionar artigos para revisão de acordo com a expertise de cada revisor.

> **Exemplo:** Se o artigo *"Padrões de Projeto Aplicados em Sistemas de Saúde"* for rotulado como *"Engenharia de Software"* e *"Design de Software"*, ele será direcionado a algum revisor que possua ao menos uma dessas habilidades.

---

### RF04 – Comitê Técnico de Avaliação

Constitui um grupo de usuários pré-cadastrados convidados para atuar na avaliação de artigos. Para facilitar a simulação, os usuários avaliadores são registrados diretamente no sistema. Na realidade, eles receberiam um e-mail com link para aceitar ou rejeitar o convite.

O comitê é gerenciado por um coordenador geral denominado **chair**, que gerencia e monitora todo o processo. Cabe a ele preparar o sistema e identificar a categoria do evento:

| Categoria | Descrição |
|---|---|
| **Full Paper** | Artigos completos com pesquisa finalizada, metodologia rigorosa, resultados maduros e discussões profundas. Limite de páginas mais extenso. |
| **Short Paper** | Versão mais concisa. Foca em ideias iniciais, trabalhos em andamento, estudos de caso preliminares ou resultados parciais com insights valiosos. |
| **Demo** | Demonstração prática e interativa. Os autores submetem o trabalho para mostrar o funcionamento de um software, hardware, protótipo ou ferramenta. |

---

### RF05 – Submissão de Artigos

Um usuário deve fazer o upload de um artigo em PDF (basta o registro do nome; não é necessário upload real), fornecer o resumo do texto e indicar os coautores (devem estar cadastrados). Os três campos são obrigatórios.

A submissão só é aceita se realizada dentro do prazo. Cada artigo submetido recebe um ID.

O autor pode consultar a qualquer momento a lista de artigos submetidos. Cada artigo terá um status:

| Status | Descrição |
|---|---|
| **Submetido** | O sistema recebeu a submissão e aguarda avaliação. O autor não recebe informações adicionais. |
| **Revisão** | O artigo foi distribuído aos revisores. O autor não recebe informações adicionais. |
| **Aceito** | Artigo aceito para publicação. O autor tem acesso ao parecer dos revisores. |
| **Rejeitado** | Artigo não aceito para publicação. O autor tem acesso ao parecer dos revisores. |

> A submissão só pode ser feita se o sistema estiver "aberto" para recebimento de artigos. Caso contrário, o usuário deve ser informado com uma mensagem adequada.

---

### RF06 – Distribuição Automática dos Artigos para Revisão

Para todos os artigos submetidos, o sistema deve distribuir automaticamente os artigos entre os revisores, conforme afinidade de tema. A distribuição deve ser igualitária (em caso de quantidade ímpar, algum revisor receberá um a mais).

Regras:
- Se houver tema sem aderência a nenhum revisor, o artigo deve ser distribuído mesmo assim;
- Um revisor **não pode** receber um artigo do qual é autor ou coautor — deve ser passado para outro;
- O revisor **não deve** saber quem são os autores do artigo que está avaliando (blind-review);
- Revisores com temas compatíveis têm prioridade.

**Exemplo de compatibilidade:**

| Artigo | Revisor |
|---|---|
| IA, Machine Learning | IA, Deep Learning, Ciência de Dados, Machine Learning |

→ O sistema deve identificar **alta compatibilidade**.

O revisor deve ser notificado por e-mail sobre cada artigo recebido, indicando o nome do artigo e o prazo máximo para concluir a revisão.

---

### RF07 – Conclusão de Avaliação

Cada revisor deve ter acesso à lista de artigos que aceitou revisar. A avaliação é concluída quando o revisor emite um **parecer**, que consiste em:

- Contribuições do artigo (texto);
- Pontos de crítica (texto);
- Veredito: **recusado**, **fracamente recusado**, **fracamente aceito** ou **aceito**.

Após o parecer, o artigo passa para o status de concluído.

---

### RF08 – Dashboard

Deve prover as seguintes informações:

- Número de artigos submetidos;
- Número de revisores;
- Número de artigos avaliados;
- Número de artigos pendentes de avaliação;
- Relação dos artigos pendentes e qual avaliador é responsável por cada um.

---

### RF09 – Notificação aos Autores

Ao término do ciclo de revisões, o sistema deve permitir a **notificação em massa** aos autores por e-mail. O envio deve ser real (não apenas uma mensagem no console).

**Modelo de e-mail – Rejeição:**

> Prezado Sr. **Alex Cunha**:
>
> Lamentamos informar que seu artigo de nº **249227** intitulado **"Um Levantamento sobre os principais erros cometidos na aplicação de padrões de projeto de software"** não pôde ser aceito para o **SBSI 2026 - Full Papers**.
>
> Ao final do e-mail, seguem os pareceres dos revisores, que esperamos que possam auxiliá-lo em futuras submissões.
>
> Agradecemos sua submissão.
>
> Atenciosamente,
> **Damires Souza**
> *Coordenadora do Comitê de Programa do SBSI 2026*
>
> ---
> **[Revisor 1]**
>
> *Principal Contribuição ou pontos positivos*
> O artigo faz um levantamento prático de vários erros de aplicação de padrões de projeto em artigos publicados em comunidades de desenvolvimento...
>
> *Pontos negativos*
> N1 - As referências usadas no trabalho são muito antigas; deveria ter abordado problemas mais atuais.
> N2 - O artigo não apresenta uma contribuição sobre como o usuário pode avaliar se um código é confiável ou não.
>
> ---
> **[Revisor 2]**
>
> *Principal Contribuição ou pontos positivos*
> O artigo tem uma boa proposta de condensar problemas no uso de padrões de projeto.
>
> *Pontos negativos*
> N1 - Observa-se problemas de coesão na escrita do texto.
> N2 - Os autores só avaliaram códigos de um único site. Deveriam ter explorado outras comunidades.

**Modelo de e-mail – Aceitação:**

> Prezado Sr. **Alex Cunha**:
>
> Parabéns! Sua submissão de nº **249227**, intitulada **"Bem-te-vejo: Conectando pessoas e profissionais de saúde mental para promover o cuidado diante de sinais de depressão"**, para o **SBSI 2026 - Full Papers**, foi aceita.
>
> As avaliações estão disponíveis ao final do e-mail.
>
> Atenciosamente,
> **Damires Souza**
> *Coordenadora do Comitê de Programa do SBSI 2026*
>
> *[Revisor 1] e [Revisor 2] — usar o mesmo formato do modelo de rejeição.*

> **Nota:** Independentemente do resultado, os autores sempre receberão as considerações dos avaliadores.

---

### RF10 – Adicionar um Novo Padrão

Adicione um novo requisito coerente e funcional no escopo do projeto, que possa ser resolvido com um padrão de projeto adicional (não utilizado para atender os requisitos anteriores). Evitar requisitos forçados sem utilidade prática.

---

## IV – Requisitos Não Funcionais

A construção do código deve contemplar os seguintes itens qualitativos:

- Aderência aos princípios SOLID;
- Promover o encapsulamento;
- Manipular exceções adequadamente;
- Promover o reuso de classes;
- Evitar acoplamento desnecessário;
- Não definir chamadas de `System.out.println()` dentro de métodos — o fluxo de mensagens deve ser exibido da forma mais adequada possível;
- Ser funcional e prover um cenário de execução de fácil entendimento quando a simulação for executada.

---

## V – Requisitos de Execução

Para facilitar a correção e reprodução em outro computador:

- **E1** – Povoar os dados automaticamente a partir de arquivos CSV, conforme o cenário de execução idealizado pela equipe. Isso evita digitações iniciais para testar o sistema.
- **E2** – Atenção à complexidade de execução por terceiros. Montar e executar o projeto na máquina do professor de forma fácil será um **quesito extra na avaliação**.

---

## VI – Entrega

Ao submeter o projeto, forneça obrigatoriamente:

- [ ] Diagrama de classes da solução, indicando onde os padrões se encaixam;
- [ ] `README.md` bem documentado com: disciplina, período, professor, equipe, classes, padrões utilizados e onde, descrição da solução, instruções de execução e especificação de como cada requisito foi resolvido;
- [ ] Pasta com todos os arquivos da aplicação (preferencialmente um link do GitHub).

> Soluções originais, mesmo que usando o mesmo padrão de outro grupo, **terão maior pontuação**.

---

## 🚨 Avisos Importantes

- O `README.md` é um dos itens de avaliação;
- Soluções que **não rodarem** no computador do professor terão redução de nota. As instruções de execução devem ser claras e suficientes;
- Soluções enviesadas observadas em todos os projetos terão menor peso (o padrão pode estar correto, mas implementações idênticas às de outras equipes terão redução);
- Submissões **após o prazo** terão redução de **1,0 ponto** na nota final. Após o prazo máximo, não serão apresentadas;
- A nota final pode ser **diferente para cada membro** da equipe.

---

## Equipes

| Equipe | Integrantes |
|---|---|
| 1 | Davi, Arthur, Heitor |
| 2 | Francisco, Jonas e Murilo |
| 3 | Pedro Artur, Pedro Lucas, Suetone |
| 4 | Carlos, José Correia, Maria Luiza |
| 5 | Daniel, Felipe Oliveira, Gabriel |
| 6 | Felipe Macedo, Júlio Cesar |
| 7 | Maria Clara, Mariana |

---

*Padrões de Projeto de Software — Prof. Alex Sandro C. Rêgo*