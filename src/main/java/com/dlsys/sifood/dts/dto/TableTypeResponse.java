package com.dlsys.sifood.dts.dto;

import com.dlsys.sifood.dts.entity.TableType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableTypeResponse extends GenericResponse{

    private TableType table;
    private  List<TableType> tables;

    public TableTypeResponse(String statusCode, String statusResponse, List<String> description, TableType table) {
        super(statusCode, statusResponse, description);
        this.table = table;
    }

    public TableTypeResponse(String statusCode, String statusResponse, List<String> description, List<TableType> tables) {
        super(statusCode, statusResponse, description);
        this.tables = tables;
    }
}
