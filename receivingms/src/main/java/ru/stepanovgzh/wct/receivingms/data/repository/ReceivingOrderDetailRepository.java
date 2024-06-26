package ru.stepanovgzh.wct.receivingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wct.receivingms.data.entity.ReceivingOrderDetail;

@Repository
public interface ReceivingOrderDetailRepository extends JpaRepository<ReceivingOrderDetail, UUID>
{
}
