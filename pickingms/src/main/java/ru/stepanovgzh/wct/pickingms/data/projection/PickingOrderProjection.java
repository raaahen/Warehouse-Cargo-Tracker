package ru.stepanovgzh.wct.pickingms.data.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.pickingms.cqrs.event.*;
import ru.stepanovgzh.wct.pickingms.cqrs.query.AllPickingOrdersQuery;
import ru.stepanovgzh.wct.pickingms.data.entity.PickingOrderDetail;
import ru.stepanovgzh.wct.pickingms.data.mapper.PickingOrderMapper;
import ru.stepanovgzh.wct.pickingms.data.model.PickingOrder;
import ru.stepanovgzh.wct.pickingms.data.repository.PickingOrderRepository;
import ru.stepanovgzh.wct.pickingms.data.view.PickingOrderView;

@Service
@RequiredArgsConstructor
public class PickingOrderProjection
{
    private final PickingOrderMapper pickingOrderMapper;
    private final PickingOrderRepository pickingOrderRepository;

    @EventHandler
    public void on(PickingOrderCreatedEvent pickingOrderCreatedEvent)
    {
        pickingOrderRepository.save(pickingOrderMapper.map(pickingOrderCreatedEvent));
    }

    @EventHandler
    public void on(DetailAddedToPickingOrderEvent detailAddedToPickingOrderEvent)
    {
        PickingOrder pickingOrder =
            pickingOrderRepository
                .findById(detailAddedToPickingOrderEvent.getPickingOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Picking order not found, id = "
                    + detailAddedToPickingOrderEvent.getPickingOrderId()));
        pickingOrder.getDetails().add(detailAddedToPickingOrderEvent.getDetail());
        pickingOrderRepository.save(pickingOrder);
    }

    @EventHandler
    public void on(DetailRemovedFromPickingOrderEvent detailRemovedFromPickingOrderEvent)
    {
        PickingOrder pickingOrder =
            pickingOrderRepository
                .findById(detailRemovedFromPickingOrderEvent.getPickingOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Picking order not found, id = "
                    + detailRemovedFromPickingOrderEvent.getPickingOrderId()));
        PickingOrderDetail detailToRemove = pickingOrder.getDetails().stream()
            .filter(detail ->
                detail.getId().equals(detailRemovedFromPickingOrderEvent.getDetailId()))
            .findFirst()
            .orElseThrow(() ->
                new EntityNotFoundException("Picking order detail not found, id = "
                    + detailRemovedFromPickingOrderEvent.getDetailId()));
        pickingOrder.removeDetail(detailToRemove);
        pickingOrderRepository.save(pickingOrder);
    }

    @EventHandler
    public void on(CargoPickedEvent cargoPickedEvent)
    {
        PickingOrder pickingOrder =
            pickingOrderRepository.findById(cargoPickedEvent.getPickingOrderId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Picking order not found, id = "
                        + cargoPickedEvent.getPickingOrderId()));
        PickingOrderDetail pickingOrderDetail = pickingOrder.getDetails().stream()
            .filter(detail -> detail.getId().equals(cargoPickedEvent.getDetailId()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException(
                "Picking order detail not found, id = " + cargoPickedEvent.getDetailId()));
        pickingOrderDetail.setPickedCargoId(cargoPickedEvent.getPickedCargoId());
        pickingOrderDetail.setSkuPickingStatus(cargoPickedEvent.getSkuPickingStatus());
        pickingOrderRepository.save(pickingOrder);
    }

    @EventHandler
    public void on(PickingOrderStatusChangedEvent pickingOrderStatusChangedEvent)
    {
        PickingOrder pickingOrder =
            pickingOrderRepository.findById(pickingOrderStatusChangedEvent.getId())
                .orElseThrow(() -> new EntityNotFoundException("Picking order not found, id = "
                    + pickingOrderStatusChangedEvent.getId()));
        pickingOrder.setStatus(pickingOrderStatusChangedEvent.getStatus());
        pickingOrderRepository.save(pickingOrder);
    }

    @EventHandler
    public void on(PickingOrderDeletedEvent pickingOrderDeletedEvent)
    {
        PickingOrder pickingOrder =
            pickingOrderRepository.findById(pickingOrderDeletedEvent.getId())
                .orElseThrow(() -> new EntityNotFoundException("Picking order not found, id = "
                    + pickingOrderDeletedEvent.getId()));
        pickingOrderRepository.delete(pickingOrder);
    }

    @QueryHandler
    public List<PickingOrderView> handlePickingOrders(AllPickingOrdersQuery allPickingOrdersQuery)
    {
        return pickingOrderRepository.findAll().stream()
            .map(pickingOrderMapper::map)
            .collect(Collectors.toList());
    }
}
