package com.mall.user.api.process.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("comm_code")
public class CommCodeEntity {

    @Id
    private Long code;

    @Column("up_code")
    private Long upCode;

    private String name;

    private String memo;

    private String status;

}
