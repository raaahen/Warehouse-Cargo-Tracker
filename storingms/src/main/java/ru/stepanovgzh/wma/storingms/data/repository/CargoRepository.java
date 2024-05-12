package ru.stepanovgzh.wma.storingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wma.storingms.data.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> 
{
}
