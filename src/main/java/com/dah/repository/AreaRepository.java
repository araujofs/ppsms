package com.dah.repository;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends ResettableRepository {

    /**
     * Recupera todas as áreas temáticas cadastradas no sistema.
     * Utilizado para listar as opções disponíveis para coordenadores e revisores.
     * @return Uma lista com todas as áreas temáticas disponíveis.
     */
    List<Area> findAll();

    /**
     * Busca uma área temática específica pelo seu identificador único.
     * @param id O identificador da área.
     * @return Um Optional contendo a área se encontrada, ou vazio caso contrário.
     */
    Optional<Area> findById(Integer id);

    /**
     * Busca um conjunto de áreas temáticas a partir de uma lista de identificadores.
     * Essencial para validar as áreas escolhidas por um autor em um artigo (RF05)
     * ou as competências de um revisor (RF03/RF06).
     * @param ids Lista de IDs das áreas desejadas.
     * @return Uma lista contendo as áreas encontradas.
     */
    List<Area> findByIds(List<Integer> ids);

    /**
     * Salva ou atualiza uma área temática no sistema.
     * @param area O objeto da área a ser persistido.
     * @return A área salva com seu identificador atribuído.
     */
    Area save(Area area);
    
    /**
     * Remove todas as áreas temáticas do sistema.
     * Implementa o contrato de ResettableRepository, sendo útil para resetar
     * estados de testes ou reinicializar o sistema conforme o RF01.
     */
    @Override
    void deleteAll();
}
