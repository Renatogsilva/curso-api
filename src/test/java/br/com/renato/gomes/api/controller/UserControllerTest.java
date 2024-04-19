package br.com.renato.gomes.api.controller;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.domain.dto.UserDTO;
import br.com.renato.gomes.api.service.UserService;
import br.com.renato.gomes.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    private static final long ID = 1L;
    private static final String NAME = "Teste Controller";
    private static final String EMAIL = "testecontroller@email.com";
    private static final String PASSWORD = "25erfd2s5";

    private User user;
    private UserDTO dto;

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenCreateThenReturnCreated() {
        Mockito.when(this.service.userCreate(dto)).thenReturn(user);

        ResponseEntity<UserDTO> response = this.controller.create(dto);

        assertNotNull(response.getHeaders().get("location"));
        assertNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void update() {
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        Mockito.when(this.service.findById(Mockito.anyLong())).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(dto);

        ResponseEntity<UserDTO> response = this.controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        Mockito.when(this.service.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException("Registro não encontrado"));

        try{
            ResponseEntity<UserDTO> response = this.controller.findById(ID);
        }catch (Exception ex){
            assertEquals("Registro não encontrado", ex.getMessage());
            assertEquals(ObjectNotFoundException.class, ex.getClass());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUserDto() {
        Mockito.when(this.service.findAll()).thenReturn(List.of(user));
        Mockito.when(this.mapper.map(Mockito.any(), Mockito.any())).thenReturn(dto);

        ResponseEntity<List<UserDTO>> response = this.controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());

        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(NAME, response.getBody().get(0).getName());
        assertEquals(EMAIL, response.getBody().get(0).getEmail());
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        dto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}