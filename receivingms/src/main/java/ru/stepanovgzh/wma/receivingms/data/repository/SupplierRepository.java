package ru.stepanovgzh.wma.receivingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wma.receivingms.data.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID>
{
}
