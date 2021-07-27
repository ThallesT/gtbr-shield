package com.gtbr.gtbrshield.resource;

import com.gtbr.gtbrshield.entity.dto.ApiDTO;
import com.gtbr.gtbrshield.entity.dto.UserDTO;
import com.gtbr.gtbrshield.exception.AuthorizationException;
import com.gtbr.gtbrshield.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/authenticate")
public class AuthenenticateResource {

    private AuthService authService;

    public AuthenenticateResource(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/api")
    public ResponseEntity loginApi(@RequestBody ApiDTO apiDTO) throws AuthorizationException {
        return ResponseEntity.ok(authService.loginApi(apiDTO.getApiId(), apiDTO.getAccessKey()));
    }

    @RequestMapping("/user")
    public ResponseEntity loginUser(@RequestBody UserDTO userDTO) throws AuthorizationException {
        return ResponseEntity.ok(authService.loginUser(userDTO.getEmail(), userDTO.getPassword()));
    }

}
