package com.example.fullstackdemo310824.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "address",schema = "aadhar")
@Data
public class AddressEntity {

    public AddressEntity(){

    }

    @Id//PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//AI
    @Column(name = "id")
    public Integer id;

    @Column(name = "state")
    public String state;


    //bi-directional
    @ManyToOne
    @JoinColumn(name = "aadharnumber",referencedColumnName = "aadharnumber")//name is child column name and referencedColumn Name is parent column name
    public AadharEntity myMappedByTestEntity;




}
