package com.dlsys.sifood.dts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DinningModel {
    private String idTableType = "";
    public String reservation = "";
    public String name = "";
    public String id = "";
    public String userCapacity = "";
}
