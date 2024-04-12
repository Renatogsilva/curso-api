package br.com.renato.gomes.api.controller;

import br.com.renato.gomes.api.domain.User;
import br.com.renato.gomes.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.ok().body(userService.userCreate(user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }
}