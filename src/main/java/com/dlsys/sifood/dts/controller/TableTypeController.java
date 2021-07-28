package com.dlsys.sifood.dts.controller;

import com.dlsys.sifood.dts.entity.TableType;
import com.dlsys.sifood.dts.model.TableTypeModel;
import com.dlsys.sifood.dts.service.tableType.ITableTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/dinning")
public class TableTypeController {

    @Autowired
    ITableTypeService tableService;

    @RequestMapping(value = "/catalog/tableType", method = RequestMethod.POST)
    public ResponseEntity<?> postProfileJob(@Valid @RequestBody TableType table, BindingResult result) {
        return tableService.postTableType(table, result);
    }

    @RequestMapping(value = "/updateInformationCatalog/tableType", method = RequestMethod.PUT)
    public ResponseEntity<?> putProfileJob(@Valid @RequestBody TableType table, BindingResult result) {
        return tableService.putPorfile(table, result);
    }

    @RequestMapping(value = "/getInformationCatalog/tableType", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileJob(@RequestBody TableTypeModel table) {
        return tableService.getProfile(table);
    }

}
