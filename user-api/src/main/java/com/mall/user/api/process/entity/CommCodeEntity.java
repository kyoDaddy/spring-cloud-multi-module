package com.mall.user.api.process.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comm_code")
public class CommCodeEntity {

    @Id
    private Long code;

    @Column(name = "up_code")
    private Long upCode;

    private String name;

    private String memo;

    private String status;

}

