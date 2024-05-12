package ru.stepanovgzh.wma.storingms.aggregate;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import ru.stepanovgzh.wma.storingms.cqrs.command.ChangeCargoStatusCommand;
import ru.stepanovgzh.wma.storingms.cqrs.command.CreateCargoCommand;
import ru.stepanovgzh.wma.storingms.cqrs.command.DeleteCargoCommand;
import ru.stepanovgzh.wma.storingms.cqrs.command.MoveCargoCommand;
import ru.stepanovgzh.wma.storingms.cqrs.command.UpdateCargoCommand;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoCreatedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoDeletedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoMovedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoStatusChangedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoUpdatedEvent;
import ru.stepanovgzh.wma.storingms.data.value.CargoStatus;
import ru.stepanovgzh.wma.storingms.data.value.Location;
import ru.stepanovgzh.wma.storingms.data.value.Pack;
import ru.stepanovgzh.wma.storingms.data.value.Sku;

@Aggregate
@NoArgsConstructor
public class CargoAggregate 
{
    @AggregateIdentifier
    private UUID id;
    Sku sku;
    Pack pack;
    int qty;
    Location location;
    CargoStatus status;

    @CommandHandler
    public CargoAggregate(CreateCargoCommand createCargoCommand)
    {
        AggregateLifecycle.apply(new CargoCreatedEvent(
            createCargoCommand.getId(),
            new Sku(createCargoCommand.getSkuBarcode(),
                createCargoCommand.getSkuName(),
                createCargoCommand.getSkuDescription()),
            new Pack(createCargoCommand.getPackType(),
                createCargoCommand.getPackDescription()),
            createCargoCommand.getQtyOfSku()));
    }

    @EventSourcingHandler
    public void on(CargoCreatedEvent cargoCreatedEvent)
    {
        this.id = cargoCreatedEvent.getId();
        this.sku = cargoCreatedEvent.getSku();
        this.pack = cargoCreatedEvent.getPack();
        this.qty = cargoCreatedEvent.getQty();
    }

    @CommandHandler
    public void handleChangeCargoStatus(ChangeCargoStatusCommand changeCargoStatusCommand)
    {
        AggregateLifecycle.apply(new CargoStatusChangedEvent(
            changeCargoStatusCommand.getId(),
            changeCargoStatusCommand.getStatus()));
    }

    @EventSourcingHandler
    public void on(CargoStatusChangedEvent cargoStatusChangedEvent)
    {
        this.status = cargoStatusChangedEvent.getStatus();
    }

    @CommandHandler
    public void handleUpdateCargo(UpdateCargoCommand updatecCargoCommand)
    {
        AggregateLifecycle.apply(new CargoUpdatedEvent(
            updatecCargoCommand.getId(),
            updatecCargoCommand.getQty()));
    }

    @EventSourcingHandler
    public void on(CargoUpdatedEvent cargoUpdatedEvent)
    {
        this.qty = cargoUpdatedEvent.getQty();
    }

    @CommandHandler
    public void handleMoveCargo(MoveCargoCommand moveCargoCommand)
    {
        AggregateLifecycle.apply(new CargoMovedEvent(
            moveCargoCommand.getId(),
            new Location(moveCargoCommand.getZoneName(), 
                moveCargoCommand.getCellName())));
    }

    @EventSourcingHandler
    public void on(CargoMovedEvent cargoMovedEvent)
    {
        this.location = cargoMovedEvent.getLocation();
    }

    @CommandHandler
    public void handleDeleteCargo(DeleteCargoCommand deleteCargoCommand)
    {
        AggregateLifecycle.apply(new CargoDeletedEvent(
            deleteCargoCommand.getId()));
    }

    @EventSourcingHandler
    public void on(CargoDeletedEvent cargoDeletedEvent)
    {
        AggregateLifecycle.markDeleted();
    }
}
