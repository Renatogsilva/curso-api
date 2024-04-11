package br.com.renato.gomes.api.service.impl;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.repository.UserRepository;
import br.com.renato.gomes.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User userCreate(User user) {
        return null;
    }

    @Override
    public User userUpdate(User user) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User findAll() {
        return null;
    }

    @Override
    public User delete(Long id) {
        return null;
    }
}
