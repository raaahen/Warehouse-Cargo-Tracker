package ru.stepanovgzh.wct.receivingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wct.receivingms.data.model.ReceivingOrder;

@Repository
public interface ReceivingOrderRepository extends JpaRepository<ReceivingOrder, UUID>
{
}
