package ru.stepanovgzh.wms.skumanagement.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wms.skumanagement.data.model.SkuGroup;

@Repository
public interface SkuGroupRepository extends JpaRepository<SkuGroup, UUID>
{
}
