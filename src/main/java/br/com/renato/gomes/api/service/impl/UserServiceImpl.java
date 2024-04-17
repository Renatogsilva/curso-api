package br.com.renato.gomes.api.service.impl;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.domain.dto.UserDTO;
import br.com.renato.gomes.api.repository.UserRepository;
import br.com.renato.gomes.api.service.UserService;
import br.com.renato.gomes.api.service.exceptions.DataIntegratyViolationException;
import br.com.renato.gomes.api.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User userCreate(UserDTO userDto) {
        this.findByEmail(userDto);
        return this.userRepository.save(mapper.map(userDto, User.class));
    }

    @Override
    public User userUpdate(UserDTO userDto) {
        return this.userRepository.save(mapper.map(userDto, User.class));
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Registro não encontrado."));
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User delete(Long id) {
        return null;
    }

    private void findByEmail(UserDTO dto){
        Optional<User> user = this.userRepository.findByEmail(dto.getEmail());
        if(user.isPresent()){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema.");
        }
    }
}
