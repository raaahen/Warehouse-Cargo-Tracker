package ru.stepanovgzh.wma.receivingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wma.receivingms.data.entity.ReceivingOrderDetail;

@Repository
public interface ReceivingOrderDetailRepository extends JpaRepository<ReceivingOrderDetail, UUID>
{
    void deleteByReceivingOrderId(UUID receivingOrderId);
}
