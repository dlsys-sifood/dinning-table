package com.dlsys.sifood.dts.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "dinnnig_table")
public class DinningTable implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID id;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String name;

    @Column(name = "user_capacity")
    @NotNull(message = "no puede ser vacio")
    private Integer userCapacity;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String image;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String description;

    @Column
    @NotNull(message = "no puede ser vacio")
    private Integer reservation;

    @Column(name = "id_table_type")
    private UUID idTableType;

    @OneToMany(mappedBy = "dinning", fetch = FetchType.LAZY)
    private List<TableGroup> group;

    private Integer flag;

    @PrePersist
    public void beforeSave(){
        this.flag = 1;
    }

    public DinningTable (){
        group = new ArrayList<>();
    }
}
