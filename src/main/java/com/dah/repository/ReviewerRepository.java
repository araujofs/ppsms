package com.dah.repository;

import java.util.List;
import java.util.Optional;

public interface ReviewerRepository extends ResettableRepository {

    /**
     * Recupera todos os revisores cadastrados no comitê técnico.
     * Utilizado para listar o corpo de avaliadores disponíveis para o sistema.
     * @return Uma lista com todos os perfis de revisor.
     */
    List<ReviewerProfile> findAll();

    /**
     * Busca o perfil de revisor associado a um usuário específico.
     * Essencial para verificar se um determinado usuário já faz parte do comitê
     * e para carregar suas áreas de expertise durante a distribuição automática (RF06).
     * @param user O usuário cujos dados de revisor se deseja consultar.
     * @return Um Optional contendo o perfil de revisor se o usuário for um avaliador,
     * ou um Optional vazio caso ele não faça parte do comitê.
     */
    Optional<ReviewerProfile> findByUser(User user);

    /**
     * Persiste ou atualiza o perfil de um revisor no sistema.
     * @param reviewer O objeto do perfil do revisor a ser salvo.
     * @return O perfil do revisor salvo com seu identificador atribuído.
     */
    ReviewerProfile save(ReviewerProfile reviewer);

    /**
     * Remove todos os revisores do comitê técnico.
     * Implementa o contrato de ResettableRepository, garantindo que a lista de
     * avaliadores possa ser limpa entre ciclos de eventos (RF01).
     */
    @Override
    void deleteAll();
}
