package com.dlsys.sifood.dts.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "table_type")
public class TableType implements Serializable {
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

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String seccion;

    private Integer flag;

    @PrePersist
    public void beforeSave(){
        this.flag = 1;
    }
}
