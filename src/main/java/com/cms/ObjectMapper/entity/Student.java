package com.cms.ObjectMapper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@ToString

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;

    @Column(columnDefinition = "TEXT")
    private String quiz;
}
