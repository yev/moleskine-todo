package com.devatlant.todo.business.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Todo{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String title;
}
