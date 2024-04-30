package ru.stepanovgzh.wms.productmanagement.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wms.productmanagement.data.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>
{
}
