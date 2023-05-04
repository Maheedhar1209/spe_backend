package com.iiitb.spe.models;

import javax.persistence.*;

@Entity
@Table(name = "user_login")
public class User_Login {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Column(name = "phone_number")
    private String phone_number;

}
