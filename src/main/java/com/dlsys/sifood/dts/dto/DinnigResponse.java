package com.dlsys.sifood.dts.dto;

import com.dlsys.sifood.dts.entity.DinningTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DinnigResponse extends GenericResponse{

    private DinningTable dinning;
    private List<DinningTable> dinnings;


    public DinnigResponse(String statusCode, String statusResponse, List<String> description, DinningTable dinning) {
        super(statusCode, statusResponse, description);
        this.dinning = dinning;
    }

    public DinnigResponse(String statusCode, String statusResponse, List<String> description, List<DinningTable> dinnings) {
        super(statusCode, statusResponse, description);
        this.dinnings = dinnings;
    }
}
