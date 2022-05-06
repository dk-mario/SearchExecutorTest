package ru.sberbank.jd.jdprofessionalsservice.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ng_users")
public class UsersEntity {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersEntity usersEntity = (UsersEntity) o;
        return login != null && Objects.equals(login, usersEntity.login);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public UsersEntity() {
    }
}
