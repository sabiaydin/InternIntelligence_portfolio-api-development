package com.example.portfolio_api_development.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
