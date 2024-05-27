package ru.stepanovgzh.wct.orderingms.data.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.orderingms.cqrs.event.CargoPickedEvent;
import ru.stepanovgzh.wct.orderingms.cqrs.event.DetailAddedToPickingOrderEvent;
import ru.stepanovgzh.wct.orderingms.cqrs.event.DetailRemovedFromPickingOrderEvent;
import ru.stepanovgzh.wct.orderingms.cqrs.event.PickingOrderCreatedEvent;
import ru.stepanovgzh.wct.orderingms.cqrs.event.PickingOrderDeletedEvent;
import ru.stepanovgzh.wct.orderingms.cqrs.query.AllPickingOrdersQuery;
import ru.stepanovgzh.wct.orderingms.data.entity.PickingOrderDetail;
import ru.stepanovgzh.wct.orderingms.data.mapper.PickingOrderMapper;
import ru.stepanovgzh.wct.orderingms.data.model.PickingOrder;
import ru.stepanovgzh.wct.orderingms.data.repository.PickingOrderDetailRepository;
import ru.stepanovgzh.wct.orderingms.data.repository.PickingOrderRepository;
import ru.stepanovgzh.wct.orderingms.data.view.PickingOrderView;

@Service
@RequiredArgsConstructor
public class PickingOrderProjection 
{
    private final PickingOrderMapper pickingOrderMapper;
    private final PickingOrderRepository pickingOrderRepository;
    private final PickingOrderDetailRepository pickingOrderDetailRepository;

    @EventHandler
    public void on(PickingOrderCreatedEvent pickingOrderCreatedEvent)
    {
        pickingOrderRepository.save(pickingOrderMapper.map(pickingOrderCreatedEvent));
    }

    @EventHandler
    public void on(DetailAddedToPickingOrderEvent detailAddedToPickingOrderEvent)
    {
        pickingOrderRepository.findById(detailAddedToPickingOrderEvent.getPickingOrderId())
            .orElseThrow(() -> new EntityNotFoundException("Picking order not found, id = " 
                + detailAddedToPickingOrderEvent.getPickingOrderId()));
        pickingOrderDetailRepository.save(detailAddedToPickingOrderEvent.getDetail());
    }

    @EventHandler
    public void on(DetailRemovedFromPickingOrderEvent detailRemovedFromPickingOrderEvent)
    {
        PickingOrder pickingOrder 
            = pickingOrderRepository
                .findById(detailRemovedFromPickingOrderEvent.getPickingOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Picking order not found, id = " 
                    + detailRemovedFromPickingOrderEvent.getPickingOrderId()));
        PickingOrderDetail detail
            = pickingOrderDetailRepository
                .findById(detailRemovedFromPickingOrderEvent.getDetailId())
                .orElseThrow(() 
                    -> new EntityNotFoundException("Picking order detail not found, id = " 
                        + detailRemovedFromPickingOrderEvent.getDetailId()));
        pickingOrderDetailRepository.delete(detail);
        pickingOrderRepository.save(pickingOrder);
    }

    @EventHandler
    public void on(CargoPickedEvent cargoPickedEvent)
    {
        PickingOrderDetail pickingOrderDetail 
            = pickingOrderDetailRepository.findById(cargoPickedEvent.getDetailId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Picking order detail not found, id = " + cargoPickedEvent.getDetailId()));
        pickingOrderDetail.setPickedCargoId(cargoPickedEvent.getPickedCargoId());
        pickingOrderDetail.setSkuPickingStatus(cargoPickedEvent.getSkuPickingStatus());
        pickingOrderDetailRepository.save(pickingOrderDetail);
    }

    @EventHandler
    public void on(PickingOrderDeletedEvent pickingOrderDeletedEvent)
    {
        PickingOrder pickingOrder 
            = pickingOrderRepository.findById(pickingOrderDeletedEvent.getId())
                .orElseThrow(() -> new EntityNotFoundException("Picking order not found, id = " 
                    + pickingOrderDeletedEvent.getId()));
        pickingOrderDetailRepository.deleteByPickingOrderId(pickingOrder.getId());
        pickingOrderRepository.delete(pickingOrder);
    }

    @QueryHandler
    public List<PickingOrderView> handleReceivingOrders(AllPickingOrdersQuery allPickingOrdersQuery)
    {
        return pickingOrderRepository.findAll().stream()
            .map(pickingOrder -> pickingOrderMapper.map(pickingOrder))
            .collect(Collectors.toList());
    }
}
