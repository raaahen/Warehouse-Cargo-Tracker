package ru.stepanovgzh.wct.pickingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wct.pickingms.data.model.PickingOrder;

@Repository
public interface PickingOrderRepository extends JpaRepository<PickingOrder, UUID> 
{
}
