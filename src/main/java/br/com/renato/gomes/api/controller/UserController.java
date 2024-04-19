package br.com.renato.gomes.api.controller;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.domain.dto.UserDTO;
import br.com.renato.gomes.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/user")
public class UserController {

    public static final String ID = "/{id}";
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(this.userService.userCreate(userDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto){
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(this.userService.userUpdate(dto), UserDTO.class));
    }

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> listDto = userService.findAll().stream().map(x -> mapper.map(x, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}