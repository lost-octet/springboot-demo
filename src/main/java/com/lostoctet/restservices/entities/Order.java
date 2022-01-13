package com.lostoctet.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order> {

    @Id
    @GeneratedValue
    private Long orderId;
    private String orderDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    //Constructors
    public Order() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public User getUser() {
        return user;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
