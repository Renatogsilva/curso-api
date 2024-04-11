package br.com.renato.gomes.api.service;

import br.com.renato.gomes.api.domain.User;

public interface UserService {
    User userCreate(User user);
    User userUpdate(User user);
    User findById(Long id);
    User findAll();
    User delete(Long id);
}
