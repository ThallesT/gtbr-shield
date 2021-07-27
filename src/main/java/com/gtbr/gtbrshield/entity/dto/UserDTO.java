package com.gtbr.gtbrshield.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gtbr.gtbrshield.entity.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private String firstLoginPassword;
    @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-br", timezone = "Brazil")
    private Date joinDate;
    @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-br", timezone = "Brazil")
    private Date statusDate;
    private UserStatusEnum userStatusEnum;
}
