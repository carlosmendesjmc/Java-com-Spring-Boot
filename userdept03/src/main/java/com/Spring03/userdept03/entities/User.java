package com.Spring03.userdept03.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.atn.ATN;
import org.hibernate.mapping.Value;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
