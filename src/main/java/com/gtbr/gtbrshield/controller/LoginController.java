package com.gtbr.gtbrshield.controller;

import com.gtbr.gtbrshield.entity.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final RestTemplate restTemplate;

    public LoginController() {
        this.restTemplate = new RestTemplate();
    }

    @RequestMapping("/user")
    public String loginUser(@RequestBody UserDTO userDTO,
                            HttpServletResponse response) {
        log.info("[LOGIN CONTROLLER] - [INICIANDO PROCESSO DE LOGIN DO USUARIO] - {}", userDTO);
        String token = null;
        try {
            log.info("[LOGIN CONTROLLER] - [INICIANDO AUTENTICACAO] - {}", userDTO);
            token = restTemplate.postForObject("http://localhost:8080/gtbr-shield/authenticate/user", userDTO, String.class);

            log.info("[LOGIN CONTROLLER] - [TOKEN] - {}", token);
            response.setHeader("access-control-expose-headers", "Authorization");
            response.setHeader("Authorization", "Bearer " + token);
        } catch (HttpServerErrorException e) {

            log.info("[LOGIN CONTROLLER] - [ERRO AO LOGAR O USUARIO]");
        }



        return null;
    }

}
