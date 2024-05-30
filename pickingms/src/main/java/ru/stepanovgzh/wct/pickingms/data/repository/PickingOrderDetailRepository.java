package ru.stepanovgzh.wct.pickingms.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.stepanovgzh.wct.pickingms.data.entity.PickingOrderDetail;

@Repository
public interface PickingOrderDetailRepository extends JpaRepository<PickingOrderDetail, UUID>
{
    void deleteByPickingOrderId(UUID pickingOrderId);
}
