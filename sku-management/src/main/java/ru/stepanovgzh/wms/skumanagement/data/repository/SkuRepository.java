package ru.stepanovgzh.wms.skumanagement.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wms.skumanagement.data.model.Sku;

@Repository
public interface SkuRepository extends JpaRepository<Sku, UUID>
{
}
