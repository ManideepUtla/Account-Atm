package com.example.fullstackdemo310824.model;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Entity
@Table(name = "account_address", schema = "bank")
@Data
public class AccountAddressEntity {
    public AccountAddressEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "add1")
    private String address1;

    @Column(name = "add2")
    private String address2;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "accountnumber",referencedColumnName = "accountnumber")
    private AccountEntity accountEntity;//bi-directional


}
