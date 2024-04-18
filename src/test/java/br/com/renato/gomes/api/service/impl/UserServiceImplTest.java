package br.com.renato.gomes.api.service.impl;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.domain.dto.UserDTO;
import br.com.renato.gomes.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    public static final long ID = 5L;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "emaill@email.com";
    public static final String PASSWORD = "154562";
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDTO dto;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void userCreate() {
    }

    @Test
    void userUpdate() {
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(this.repository.findById(Mockito.anyLong())).thenReturn(optionalUser);

        User response = this.service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    private void startUsers(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        dto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}