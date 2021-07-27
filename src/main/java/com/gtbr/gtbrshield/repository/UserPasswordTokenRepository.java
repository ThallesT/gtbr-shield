package com.gtbr.gtbrshield.repository;

import com.gtbr.gtbrshield.entity.UserPasswordToken;
import com.gtbr.gtbrshield.entity.enums.StandardStatusEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPasswordTokenRepository extends CrudRepository<UserPasswordToken, Long> {
    UserPasswordToken findUserPasswordTokenByTokenAndStatus(String token, StandardStatusEnum status);
}
