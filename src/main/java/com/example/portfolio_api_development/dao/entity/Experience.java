package com.example.portfolio_api_development.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "experiences")
@Data
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String companyName;
    private String role;
    private String startDate;
    private String endDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
