package ru.stepanovgzh.wct.receivingms.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.receivingms.cqrs.event.CargoReceivedEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.DetailAddedToReceivingOrderEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.DetailRemovedFromReceivingOrderEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.ReceivingOrderCreatedEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.ReceivingOrderDeletedEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.query.AllReceivingOrdersQuery;
import ru.stepanovgzh.wct.receivingms.data.entity.ReceivingOrderDetail;
import ru.stepanovgzh.wct.receivingms.data.mapper.ReceivingOrderMapper;
import ru.stepanovgzh.wct.receivingms.data.model.ReceivingOrder;
import ru.stepanovgzh.wct.receivingms.data.repository.ReceivingOrderDetailRepository;
import ru.stepanovgzh.wct.receivingms.data.repository.ReceivingOrderRepository;
import ru.stepanovgzh.wct.receivingms.data.view.ReceivingOrderView;

@Service
@RequiredArgsConstructor
public class ReceivingOrderProjection 
{
    private final ReceivingOrderMapper receivingOrderMapper;
    private final ReceivingOrderRepository receivingOrderRepository;

    @EventHandler
    public void on(ReceivingOrderCreatedEvent receivingOrderCreatedEvent)
    {
        receivingOrderRepository.save(receivingOrderMapper.map(receivingOrderCreatedEvent));
    }

    @EventHandler
    public void on(DetailAddedToReceivingOrderEvent detailAddedToReceivingOrderEvent)
    {
        ReceivingOrder receivingOrder =
            receivingOrderRepository
                .findById(detailAddedToReceivingOrderEvent.getReceivingOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Receiving order not found, id = "
                    + detailAddedToReceivingOrderEvent.getReceivingOrderId()));
        receivingOrder.getDetails().add(detailAddedToReceivingOrderEvent.getDetail());
        receivingOrderRepository.save(receivingOrder);
    }

    @EventHandler
    public void on(DetailRemovedFromReceivingOrderEvent detailRemovedFromReceivingOrderEvent)
    {
        ReceivingOrder receivingOrder 
            = receivingOrderRepository
                .findById(detailRemovedFromReceivingOrderEvent.getReceivingOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Receiving order not found, id = " 
                    + detailRemovedFromReceivingOrderEvent.getReceivingOrderId()));
        ReceivingOrderDetail detailToRemove = receivingOrder.getDetails().stream()
            .filter(detail ->
                detail.getId().equals(detailRemovedFromReceivingOrderEvent.getDetailId()))
            .findFirst()
            .orElseThrow(() ->
                new EntityNotFoundException("Receiving order detail not found, id = "
                    + detailRemovedFromReceivingOrderEvent.getDetailId()));
        receivingOrder.getDetails().remove(detailToRemove);
        receivingOrderRepository.save(receivingOrder);
    }

    @EventHandler
    public void on(CargoReceivedEvent cargoReceivedEvent)
    {
        ReceivingOrder receivingOrder =
            receivingOrderRepository.findById(cargoReceivedEvent.getReceivingOrderId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Receiving order not found, id = "
                        + cargoReceivedEvent.getReceivingOrderId()));
        ReceivingOrderDetail receivingOrderDetail = receivingOrder.getDetails().stream()
            .filter(detail -> detail.getId().equals(cargoReceivedEvent.getDetailId()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException(
                "Receiving order detail not found, id = " + cargoReceivedEvent.getDetailId()));
        receivingOrderDetail.setReceivedCargoId(cargoReceivedEvent.getReceivedCargoId());
        receivingOrderDetail.setSkuReceivingStatus(cargoReceivedEvent.getSkuReceivingStatus());
        receivingOrderRepository.save(receivingOrder);
    }

    @EventHandler
    public void on(ReceivingOrderDeletedEvent receivingOrderDeletedEvent)
    {
        ReceivingOrder receivingOrder 
            = receivingOrderRepository.findById(receivingOrderDeletedEvent.getId())
                .orElseThrow(() -> new EntityNotFoundException("Receiving order not found, id = " 
                    + receivingOrderDeletedEvent.getId()));
        receivingOrderRepository.delete(receivingOrder);
    }

    @QueryHandler
    public List<ReceivingOrderView> handleReceivingOrders(
        AllReceivingOrdersQuery allReceivingOrdersQuery)
    {
        return receivingOrderRepository.findAll().stream()
            .map(receivingOrderMapper::map)
            .collect(Collectors.toList());
    }
}
