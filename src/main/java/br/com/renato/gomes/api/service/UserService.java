package br.com.renato.gomes.api.service;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User userCreate(UserDTO userDto);
    User userUpdate(UserDTO userDto);
    User findById(Long id);
    List<User> findAll();
    void delete(Long id);
}
