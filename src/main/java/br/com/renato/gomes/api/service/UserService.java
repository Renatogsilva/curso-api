package br.com.renato.gomes.api.service;

import br.com.renato.gomes.api.domain.User;

import java.util.List;

public interface UserService {
    User userCreate(User user);
    User userUpdate(User user);
    User findById(Long id);
    List<User> findAll();
    User delete(Long id);
}
