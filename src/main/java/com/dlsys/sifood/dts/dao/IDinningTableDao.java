package com.dlsys.sifood.dts.dao;


import com.dlsys.sifood.dts.entity.DinningTable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IDinningTableDao extends CrudRepository<DinningTable, UUID>, JpaSpecificationExecutor<DinningTable> {
}
