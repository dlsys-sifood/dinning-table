package com.dlsys.sifood.dts.response;

import com.dlsys.sifood.dts.dto.DinnigResponse;
import com.dlsys.sifood.dts.dto.GenericResponse;
import com.dlsys.sifood.dts.dto.TableTypeResponse;
import com.dlsys.sifood.dts.entity.DinningTable;
import com.dlsys.sifood.dts.entity.TableType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EntityResponse {

    private static final String BADREQUESTCODE = HttpStatus.BAD_REQUEST.toString();
    private static final String BADREQUESTDESCRIPTION = "BAD REQUEST";

    private static final String OKREQUESTCODE = HttpStatus.OK.toString();
    private static final String OKREQUESTDESCRIPTION = "OK";

    public static ResponseEntity<?> getErrorsFieldResponse(BindingResult result){
        return new ResponseEntity<Map<String, Object>>(ListResponse
                .responseGeneric(new GenericResponse(BADREQUESTCODE, BADREQUESTDESCRIPTION,
                        result.getFieldErrors().stream()
                                .map(e -> "el campo: " + e.getField() + " " + e.getDefaultMessage())
                                .collect(Collectors.toList())))
                , HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> getSuccessfullListDinningTable(List<DinningTable> response){
        return new ResponseEntity<>(ListResponse
                .responseDinningTable(new DinnigResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("Consulta encontrada"), response)), HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullDinningTable(DinningTable response){
        return new ResponseEntity<Map<String, Object>>(ListResponse
                .responseDinningTable(new DinnigResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("guardado exitoso en el tipo de tablo"), response))
                , HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullListTableType(List<TableType> response){
        return new ResponseEntity<Map<String, Object>>(ListResponse
                .responseTableType(new TableTypeResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("actualizado exitoso en el tipo de tablo"), response))
                , HttpStatus.OK);
    }

    public static ResponseEntity<?> getSuccessfullListTableType(TableType response){
        return new ResponseEntity<Map<String, Object>>(ListResponse
                .responseTableType(new TableTypeResponse(OKREQUESTCODE, OKREQUESTDESCRIPTION,
                        GenericResponse.toList("guardado exitoso en el tipo de tablo"), response))
                , HttpStatus.OK);
    }
}
