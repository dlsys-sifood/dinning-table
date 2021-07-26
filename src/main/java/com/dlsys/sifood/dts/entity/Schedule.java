package com.dlsys.sifood.dts.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String name;

    @Column
    private Integer flag;

    @PrePersist
    public void beforeSave(){
        this.flag = 1;
    }
}
