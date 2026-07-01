package com.dah.repository;

import java.util.List;

public interface ArticleRepository extends ResettableRepository {

    /**
     * Recupera todos os artigos cadastrados no sistema, independente do status.
     * @return Uma lista contendo todos os artigos.
     */
    List<Article> findAll();

    /**
     * Busca todos os artigos submetidos por um autor específico.
     * Utilizado para que o autor possa consultar seu histórico de submissões (RF05).
     * @param author O usuário que realizou a submissão.
     * @return Uma lista com os artigos do autor.
     */
    List<Article> findByAuthor(User author);

    /**
     * Busca todos os artigos que ainda aguardam avaliação (status 'Submetido' ou 'Revisão').
     * Essencial para o coordenador monitorar o progresso e para o processo de 
     * distribuição automática (RF06).
     * @return Lista de artigos pendentes de parecer final.
     */
    List<Article> findPending();

    /**
     * Busca todos os artigos que já possuem uma avaliação concluída (status 'Aceito' ou 'Rejeitado').
     * Utilizado para relatórios finais e notificações aos autores (RF09).
     * @return Lista de artigos com avaliação finalizada.
     */
    List<Article> findFinished();

    /**
     * Salva ou atualiza um artigo no sistema.
     * Quando um artigo é submetido, recebe um ID único (RF05).
     * @param article O objeto artigo a ser persistido.
     * @return O artigo salvo com seu ID e status atualizados.
     */
    Article save(Article article);

    /**
     * Remove todos os artigos do sistema.
     * Implementa o contrato de ResettableRepository, essencial para resetar
     * o banco de dados de artigos entre ciclos de eventos (RF01).
     */
    @Override
    void deleteAll();
}
