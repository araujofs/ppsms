package com.dah.repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    /**
     * Busca todos os usuários cadastrados.
     * @return Uma lista contendo todos os objetos User.
     */
    List<User> findAll();

    /**
     * Busca um usuário pelo seu identificador único.
     * * @param id O identificador do usuário a ser buscado.
     * @return Um Optional contendo o usuário, se encontrado; 
     * ou um Optional vazio, se não houver usuário com esse ID.
     */
    Optional<User> findById(Integer id);

    /**
     * Busca um usuário pelo seu email.
     * * @param email O email do usuário a ser buscado.
     * @return Um Optional contendo o usuário, se encontrado; 
     * ou um Optional vazio, se não houver usuário com esse email.
     */
    Optional<User> findByEmail(String email);

    /**
     * Salva ou atualiza um usuário no repositório.
     * * @param user O objeto usuário a ser salvo.
     * @return O objeto usuário salvo, com seu identificador.
     */
    User save(User user);
}
