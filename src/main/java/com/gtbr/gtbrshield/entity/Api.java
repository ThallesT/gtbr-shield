package com.gtbr.gtbrshield.entity;

import com.gtbr.gtbrshield.entity.enums.StandardStatusEnum;
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
@Table(name = "api")
public class Api {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long apiId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String url;

    @Column
    private StandardStatusEnum status;

    @Column
    private Date integrationDate;

    @Column
    private Date statusDate;

    @Column
    private String accessKey;

}
