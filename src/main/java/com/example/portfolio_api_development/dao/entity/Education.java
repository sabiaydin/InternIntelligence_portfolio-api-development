package com.example.portfolio_api_development.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "educations")
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String institutionName;
    private String degree;
    private String startDate;
    private String endDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
