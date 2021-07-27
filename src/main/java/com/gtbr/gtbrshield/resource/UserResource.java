package com.gtbr.gtbrshield.resource;

import com.gtbr.gtbrshield.entity.dto.UserDTO;
import com.gtbr.gtbrshield.exception.UserRegisteredException;
import com.gtbr.gtbrshield.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set-first-password")
    public ResponseEntity<Boolean> setPassword(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.setFirstPasswordToUser(userDTO.getEmail(), userDTO.getPassword()));
    }

    @PostMapping("/insert-user")
    public ResponseEntity<Boolean> insertUser(@RequestBody UserDTO userDTO) throws UserRegisteredException {
        return ResponseEntity.ok(userService.insertUser(userDTO));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserDTO>> getUserList() {
        return ResponseEntity.ok(userService.findAll());
    }
}
