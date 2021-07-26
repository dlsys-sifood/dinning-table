package com.dlsys.sifood.dts.dao;

import com.dlsys.sifood.dts.entity.TableGroup;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ITableGroupDao extends CrudRepository<TableGroup, UUID>, JpaSpecificationExecutor<TableGroup> {
}
