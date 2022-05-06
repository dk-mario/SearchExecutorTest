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
@Table(name = "ng_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "discription")
    private String discription;

    @Column(name = "status")
    private String status;

    @Column(name = "max_price")
    private int maxPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Order() {
        //this.login = "Заказчик Иван Заказной";
        this.createDate = new Date(System.currentTimeMillis());
        this.status = "Заказ размещен";
    }
    public Order(Order order, String status){
        this.id = order.getId();
        this.login = order.login;
        this.createDate = order.createDate;
        this.speciality = order.getSpeciality();
        this.discription = order.getDiscription();
        this.status = status;
        this.maxPrice = order.getMaxPrice();
    }

}
