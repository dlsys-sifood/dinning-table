package com.dlsys.sifood.dts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "table_group")
public class TableGroup implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID id;

    @Column()
    private UUID idUser;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dinnnig_table")
    private DinningTable dinning;

    public TableGroup(UUID idUser, Integer flag) {
        this.idUser = idUser;
        this.flag = flag;
    }

    @Column()
    private Integer flag;

    public TableGroup() {
    }
}