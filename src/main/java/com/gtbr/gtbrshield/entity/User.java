package com.gtbr.gtbrshield.entity;

import com.gtbr.gtbrshield.entity.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GTBR_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Date joinDate;

    @Column
    private Date statusDate;

    @Column
    private UserStatusEnum userStatus;

    @Column
    private String password;

    @Column
    private String firstLoginPassword;

}
