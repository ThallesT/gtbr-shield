package com.gtbr.gtbrshield.entity;

import com.gtbr.gtbrshield.entity.enums.StandardStatusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_password_token")
public class UserPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userPasswordTokenId;

    @Column
    private String token;

    @Column
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column
    private Date statusDate;

    @Column
    private StandardStatusEnum status;

}
