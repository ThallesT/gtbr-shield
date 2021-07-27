package com.gtbr.gtbrshield.service;

import com.gtbr.gtbrshield.entity.User;
import com.gtbr.gtbrshield.entity.UserPasswordToken;
import com.gtbr.gtbrshield.entity.dto.UserDTO;
import com.gtbr.gtbrshield.entity.enums.StandardStatusEnum;
import com.gtbr.gtbrshield.entity.enums.UserStatusEnum;
import com.gtbr.gtbrshield.exception.PasswordResetException;
import com.gtbr.gtbrshield.exception.UserRegisteredException;
import com.gtbr.gtbrshield.repository.UserPasswordTokenRepository;
import com.gtbr.gtbrshield.repository.UserRepository;
import com.gtbr.gtbrshield.util.GeneralUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserPasswordTokenRepository userPasswordTokenRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserDTO> findAll() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        userList.forEach(user -> {
            userDTOList.add(UserDTO.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .firstLoginPassword(user.getFirstLoginPassword())
                    .joinDate(user.getJoinDate())
                    .statusDate(user.getStatusDate())
                    .userStatusEnum(user.getUserStatus())
                    .build());
        });

        return userDTOList;
    }

    public Boolean setFirstPasswordToUser(String email, String password) {
        User user = findUserByEmail(email);
        if(!user.getUserStatus().equals(UserStatusEnum.FIRST_TIME_LOGIN)) return false;

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setFirstLoginPassword(null);
        user.setUserStatus(UserStatusEnum.ACTIVE);

        userRepository.save(user);

        return true;
    }

    public Boolean updatePassword(String email, String password, String token) throws PasswordResetException {
        UserPasswordToken userPasswordToken = userPasswordTokenRepository.findUserPasswordTokenByTokenAndStatus(token, StandardStatusEnum.AVAILABLE);
        if (userPasswordToken == null) throw new PasswordResetException("There is no active password resets for this account");

        User user = findUserByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);

        return true;
    }

    public Boolean insertUser(UserDTO userDTO) throws UserRegisteredException {

        if (findUserByEmail(userDTO.getEmail()) != null)
            throw new UserRegisteredException("Already has a user with this email");

        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .firstLoginPassword(GeneralUtils.generatePin())
                .joinDate(new Date())
                .statusDate(new Date())
                .userStatus(UserStatusEnum.FIRST_TIME_LOGIN)
                .build();

        userRepository.save(user);

        return true;

    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
