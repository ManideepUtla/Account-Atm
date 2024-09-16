package com.example.fullstackdemo310824.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table (name = "adhar",schema = "aadhar")
@Data
public class AadharEntity {

    public AadharEntity(){
    }

    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// AutoIncrement(AI)
    @Column(name = "aadharnumber") //ColumnName
    public Integer aadharNumber;

    @Column(name = "name")
    public String name;

    @OneToMany(mappedBy = "myMappedByTestEntity",cascade = CascadeType.ALL) // myMappedByTestEntity it is a property or
                                                 //Object reference in the child in @joinColumn
    public List<AddressEntity> addressEntityList;
}
