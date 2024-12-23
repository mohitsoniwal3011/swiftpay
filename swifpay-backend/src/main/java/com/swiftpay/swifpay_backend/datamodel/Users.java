package com.swiftpay.swifpay_backend.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "users")
@Table(name = "users")
@ToString
@EqualsAndHashCode(callSuper = false)
public class Users extends BaseResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private transient String password;

}
