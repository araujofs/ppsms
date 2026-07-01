package com.dah.repository;

import java.util.Optional;

public interface SubmissionEventRepository {

    /**
     * Persiste ou atualiza os dados do evento de submissão atual.
     * Este método deve armazenar as informações como nome, cidade e período,
     * garantindo que o sistema esteja preparado para receber submissões.
     * * @param event O objeto contendo os dados do novo evento (ex: SBSI 2026).
     */
    void saveCurrent(SubmissionEvent event);

    /**
     * Recupera o evento de submissão atualmente configurado no sistema.
     * Utilizado para verificar se o sistema está "aberto" para recebimento de artigos
     * e para validar os prazos de submissão conforme definido no RF05.
     * * @return Um Optional contendo o evento atual se ele estiver configurado,
     * ou um Optional vazio caso nenhum evento tenha sido iniciado.
     */
    Optional<SubmissionEvent> findCurrent();
}
