package com.gtbr.gtbrshield.service;

import com.gtbr.gtbrshield.entity.Api;
import com.gtbr.gtbrshield.entity.User;
import com.gtbr.gtbrshield.entity.enums.StandardStatusEnum;
import com.gtbr.gtbrshield.entity.enums.UserStatusEnum;
import com.gtbr.gtbrshield.exception.AuthorizationException;
import com.gtbr.gtbrshield.exception.UserRegisteredException;
import com.gtbr.gtbrshield.repository.ApiRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final ApiRepository apiRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(ApiRepository apiRepository, UserService userService, JwtService jwtService) {
        this.apiRepository = apiRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String loginApi(Long apiId, String accessKey) throws AuthorizationException {
        Optional<Api> api = apiRepository.findById(apiId);
        if(!api.get().getAccessKey().equals(accessKey)) throw new AuthorizationException("Api not authorized");

        return jwtService.generateApiToken(api.get());
    }

    public String loginUser(String email, String password) throws AuthorizationException {
        User user = userService.findUserByEmail(email);
        if(user == null) throw new AuthorizationException("User do not exist");
        if(user.getUserStatus().equals(UserStatusEnum.ACTIVE) || user.getUserStatus().equals(UserStatusEnum.FIRST_TIME_LOGIN)){
            if(user.getUserStatus().equals(UserStatusEnum.FIRST_TIME_LOGIN)){
                if(user.getFirstLoginPassword().equals(password)) return jwtService.generateUserToken(user);
                throw new AuthorizationException("Access denied - Wrong credentials");
            }

            if(bCryptPasswordEncoder.matches(password, user.getPassword())) return jwtService.generateUserToken(user);

            throw new AuthorizationException("Access denied - Wrong credentials");
        }

        throw new AuthorizationException("User disabled or banned, please contact us to understand");
    }
}
