package ru.stepanovgzh.wct.receivingms.aggregate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import ru.stepanovgzh.wct.receivingms.cqrs.command.AddDetailToReceivingOrderCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.command.CreateReceivingOrderCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.command.ReceiveCargoCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.command.RemoveDetailFromReceivingOrderCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.event.CargoReceivedEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.DetailAddedToReceivingOrderEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.DetailRemovedFromReceivingOrderEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.ReceivingOrderCreatedEvent;
import ru.stepanovgzh.wct.receivingms.cqrs.event.ReceivingOrderDeletedEvent;
import ru.stepanovgzh.wct.receivingms.data.entity.ReceivingOrderDetail;
import ru.stepanovgzh.wct.receivingms.data.entity.Supplier;
import ru.stepanovgzh.wct.receivingms.data.entity.Transporter;
import ru.stepanovgzh.wct.receivingms.data.value.Pack;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;
import ru.stepanovgzh.wct.receivingms.data.value.Sku;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class ReceivingOrderAggregate 
{
    @TargetAggregateIdentifier
    UUID id;
    Supplier supplier;
    Transporter transporter;
    Date date;
    ReceivingStatus status;
    List<ReceivingOrderDetail> details = new ArrayList<>();

    @CommandHandler
    public ReceivingOrderAggregate(CreateReceivingOrderCommand createReceivingOrderCommand)
    {
        apply(new ReceivingOrderCreatedEvent(
            createReceivingOrderCommand.getId(),
            new Supplier(createReceivingOrderCommand.getSupplierId(),
                createReceivingOrderCommand.getSupplierName()),
            new Transporter(createReceivingOrderCommand.getTransporterId(),
                createReceivingOrderCommand.getTransporterName()),
            createReceivingOrderCommand.getDate()));
    }

    @EventSourcingHandler
    public void on(ReceivingOrderCreatedEvent receivingOrderCreatedEvent)
    {
        this.id = receivingOrderCreatedEvent.getId();
        this.supplier = receivingOrderCreatedEvent.getSupplier();
        this.transporter = receivingOrderCreatedEvent.getTransporter();
        this.date = receivingOrderCreatedEvent.getDate();
    }

    @CommandHandler
    public void handle(AddDetailToReceivingOrderCommand addDetailToReceivingOrderCommand)
    {
        apply(new DetailAddedToReceivingOrderEvent(
            addDetailToReceivingOrderCommand.getReceivingOrderId(),
            new ReceivingOrderDetail(
                addDetailToReceivingOrderCommand.getDetailId(),
                addDetailToReceivingOrderCommand.getReceivingOrderId(),
                new Sku(addDetailToReceivingOrderCommand.getSkuBarcode(),
                    addDetailToReceivingOrderCommand.getSkuName(),
                    addDetailToReceivingOrderCommand.getSkuDecription()),
                addDetailToReceivingOrderCommand.getQty(),
                new Pack(addDetailToReceivingOrderCommand.getPackType(),
                    addDetailToReceivingOrderCommand.getPackDescription()))));
    }

    @EventSourcingHandler
    public void on(DetailAddedToReceivingOrderEvent detailAddedToReceivingOrderEvent)
    {
        details.add(detailAddedToReceivingOrderEvent.getDetail());
    }

    @CommandHandler
    public void handle(RemoveDetailFromReceivingOrderCommand removeDetailFromReceivingOrderCommand)
    {
        apply(new DetailRemovedFromReceivingOrderEvent(
            removeDetailFromReceivingOrderCommand.getReceivingOrderId(),
            removeDetailFromReceivingOrderCommand.getDetailId()));
    }

    @EventSourcingHandler
    public void on(DetailRemovedFromReceivingOrderEvent detailRemovedFromReceivingOrderEvent)
    {
        details.removeIf(
            detail -> detail.getId().equals(detailRemovedFromReceivingOrderEvent.getDetailId()));
    }

    @CommandHandler
    public void handle(ReceiveCargoCommand receiveCargoCommand)
    { 
        apply(new CargoReceivedEvent(
            receiveCargoCommand.getReceivingOrderId(), 
            receiveCargoCommand.getDetailId(), 
            receiveCargoCommand.getCargoId(), 
            receiveCargoCommand.getSkuReceivingStatus()));
    }

    @EventSourcingHandler
    public void on(CargoReceivedEvent cargoReceivedEvent) 
    {
        this.details.stream()
            .filter(detail -> detail.getId().equals(cargoReceivedEvent.getDetailId()))
            .findFirst()
            .ifPresent(detail -> 
                {
                    detail.setReceivedCargoId(cargoReceivedEvent.getReceivedCargoId());
                    detail.setSkuReceivingStatus(cargoReceivedEvent.getSkuReceivingStatus());
                });
    }

    @EventSourcingHandler
    public void on(ReceivingOrderDeletedEvent receivingOrderDeletedEvent)
    {
        AggregateLifecycle.markDeleted();
    }
}
