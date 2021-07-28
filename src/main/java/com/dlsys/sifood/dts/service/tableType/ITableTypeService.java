package com.dlsys.sifood.dts.service.tableType;

import com.dlsys.sifood.dts.entity.TableType;
import com.dlsys.sifood.dts.model.TableTypeModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ITableTypeService {
    public ResponseEntity<?> postTableType(TableType table, BindingResult result);
    public ResponseEntity<?> putPorfile(TableType table, BindingResult result);
    public ResponseEntity<?> getProfile(TableTypeModel table);
}
