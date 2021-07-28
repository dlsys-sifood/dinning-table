package com.dlsys.sifood.dts.service.dinningTable;

import com.dlsys.sifood.dts.entity.DinningTable;
import com.dlsys.sifood.dts.model.DinningModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IDinningTableService {
    public ResponseEntity<?> postDinningTable(DinningTable dinning, BindingResult result);
    public ResponseEntity<?> putDinningTable(DinningTable dinning, BindingResult result);
    public ResponseEntity<?> getDinningTable(DinningModel dinning);
}
