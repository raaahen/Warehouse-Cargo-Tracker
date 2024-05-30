package ru.stepanovgzh.wct.pickingms.aggregate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import ru.stepanovgzh.wct.pickingms.cqrs.command.AddDetailToPickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.CreatePickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.DeletePickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.PickCargoCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.RemoveDetailFromPickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.event.CargoPickedEvent;
import ru.stepanovgzh.wct.pickingms.cqrs.event.DetailAddedToPickingOrderEvent;
import ru.stepanovgzh.wct.pickingms.cqrs.event.DetailRemovedFromPickingOrderEvent;
import ru.stepanovgzh.wct.pickingms.cqrs.event.PickingOrderCreatedEvent;
import ru.stepanovgzh.wct.pickingms.cqrs.event.PickingOrderDeletedEvent;
import ru.stepanovgzh.wct.pickingms.data.entity.Client;
import ru.stepanovgzh.wct.pickingms.data.entity.PickingOrderDetail;
import ru.stepanovgzh.wct.pickingms.data.entity.Transporter;
import ru.stepanovgzh.wct.pickingms.data.value.Pack;
import ru.stepanovgzh.wct.pickingms.data.value.PickingStatus;
import ru.stepanovgzh.wct.pickingms.data.value.Sku;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class PickingOrderAggregate 
{
    @AggregateIdentifier
    UUID id;
    Client client;
    Transporter transporter;
    Date preparationDeadline;
    PickingStatus status;
    List<PickingOrderDetail> details = new ArrayList<>();

    @CommandHandler
    public PickingOrderAggregate(CreatePickingOrderCommand createPickingOrderCommand)
    {
        apply(new PickingOrderCreatedEvent(
            createPickingOrderCommand.getId(),
            new Client(
                createPickingOrderCommand.getClientId(),
                createPickingOrderCommand.getClientName()),
            new Transporter(
                createPickingOrderCommand.getTransporterId(),
                createPickingOrderCommand.getTransporterName()),
            createPickingOrderCommand.getPreparationDeadline()));
    }

    @EventSourcingHandler
    public void on(PickingOrderCreatedEvent pickingOrderCreatedEvent)
    {
        this.id = pickingOrderCreatedEvent.getId();
        this.client = pickingOrderCreatedEvent.getClient();
        this.transporter = pickingOrderCreatedEvent.getTransporter();
        this.preparationDeadline = pickingOrderCreatedEvent.getPreparationDeadline();
    }

    @CommandHandler
    public void handle(AddDetailToPickingOrderCommand addDetailToPickingOrderCommand)
    {
        apply(new DetailAddedToPickingOrderEvent(
            addDetailToPickingOrderCommand.getPickingOrderId(),
            new PickingOrderDetail(
                addDetailToPickingOrderCommand.getDetailId(),
                addDetailToPickingOrderCommand.getPickingOrderId(),
                new Sku(
                    addDetailToPickingOrderCommand.getSkuBarcode(),
                    addDetailToPickingOrderCommand.getSkuName(),
                    addDetailToPickingOrderCommand.getSkuDescription()),
                addDetailToPickingOrderCommand.getQty(),
                new Pack(
                    addDetailToPickingOrderCommand.getPackType(),
                    addDetailToPickingOrderCommand.getPackDescription()))));
    }

    @EventSourcingHandler
    public void on(DetailAddedToPickingOrderEvent detailAddedToPickingOrderEvent)
    {
        details.add(detailAddedToPickingOrderEvent.getDetail());
    }

    @CommandHandler
    public void handle(RemoveDetailFromPickingOrderCommand removeDetailFromPickingOrderCommand)
    {
        apply(new DetailRemovedFromPickingOrderEvent(
            removeDetailFromPickingOrderCommand.getPickingOrderId(),
            removeDetailFromPickingOrderCommand.getDetailId()));
    }

    @EventSourcingHandler
    public void on(DetailRemovedFromPickingOrderEvent detailRemovedFromPickingOrderEvent)
    {
        details.removeIf(
            detail -> detail.getId().equals(detailRemovedFromPickingOrderEvent.getDetailId()));
    }

    @CommandHandler
    public void handle(PickCargoCommand pickCargoCommand)
    { 
        apply(new CargoPickedEvent(
            pickCargoCommand.getPickingOrderId(), 
            pickCargoCommand.getDetailId(), 
            pickCargoCommand.getCargoId(), 
            pickCargoCommand.getSkuPickingStatus()));
    }

    @EventSourcingHandler
    public void on(CargoPickedEvent cargoPickedEvent) 
    {
        this.details.stream()
            .filter(detail -> detail.getId().equals(cargoPickedEvent.getDetailId()))
            .findFirst()
            .ifPresent(detail -> 
                {
                    detail.setPickedCargoId(cargoPickedEvent.getPickedCargoId());
                    detail.setSkuPickingStatus(cargoPickedEvent.getSkuPickingStatus());
                });
    }

    @CommandHandler
    public void handle(DeletePickingOrderCommand command)
    {
        apply(new PickingOrderDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(PickingOrderDeletedEvent pickingOrderDeletedEvent)
    {
        AggregateLifecycle.markDeleted();
    }
}
