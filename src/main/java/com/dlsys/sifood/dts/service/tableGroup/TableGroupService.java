package com.dlsys.sifood.dts.service.tableGroup;

import com.dlsys.sifood.dts.entity.TableGroup;
import com.dlsys.sifood.dts.service.tableGroup.ITableGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class TableGroupService implements ITableGroupService {
    @Override
    public ResponseEntity<?> postTableGroup(TableGroup table, BindingResult result) {
        return null;
    }

    @Override
    public ResponseEntity<?> putTableGroup(TableGroup table, BindingResult result) {
        return null;
    }

    @Override
    public ResponseEntity<?> getTableGroup(TableGroup table) {
        return null;
    }
}
