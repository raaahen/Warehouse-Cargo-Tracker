package ru.stepanovgzh.wct.orderingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wct.orderingms.data.entity.PickingOrderDetail;

@Repository
public interface PickingOrderDetailRepository extends JpaRepository<PickingOrderDetail, UUID>
{
    void deleteByPickingOrderId(UUID pickingOrderId);
}
