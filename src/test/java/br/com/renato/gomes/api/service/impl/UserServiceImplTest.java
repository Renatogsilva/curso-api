package br.com.renato.gomes.api.service.impl;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.domain.dto.UserDTO;
import br.com.renato.gomes.api.repository.UserRepository;
import br.com.renato.gomes.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    public static final long ID = 5L;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "emaill@email.com";
    public static final String PASSWORD = "154562";
    public static final String REGISTRO_NAO_ENCONTRADO = "Registro não encontrado.";
    public static final int INDEX = 0;

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
    void whenCreateThenReturnSuccess() {
        Mockito.when(this.repository.save(Mockito.any())).thenReturn(user);

        User response = this.service.userCreate(dto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
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
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        Mockito.when(this.repository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(REGISTRO_NAO_ENCONTRADO));

        try{
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(REGISTRO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        Mockito.when(this.repository.findAll()).thenReturn(List.of(user));

        List<User> response = this.service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
    }

    @Test
    void whenFindAllThenReturnAnListOfUserZero(){
        Mockito.when(this.repository.findAll()).thenReturn(List.of());

        List<User> response = this.service.findAll();

        assertNotNull(response);
        assertEquals(0, response.size());
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