package com.example.portfolio_api_development.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "skills")
@Data
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String proficiencyLevel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
