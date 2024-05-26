package ru.stepanovgzh.wct.storingms.aggregate;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import ru.stepanovgzh.wct.storingms.cqrs.command.ChangeCargoStatusCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.CreateCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.DeleteCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.MoveCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.UpdateCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.event.CargoCreatedEvent;
import ru.stepanovgzh.wct.storingms.cqrs.event.CargoDeletedEvent;
import ru.stepanovgzh.wct.storingms.cqrs.event.CargoMovedEvent;
import ru.stepanovgzh.wct.storingms.cqrs.event.CargoStatusChangedEvent;
import ru.stepanovgzh.wct.storingms.cqrs.event.CargoUpdatedEvent;
import ru.stepanovgzh.wct.storingms.data.value.CargoStatus;
import ru.stepanovgzh.wct.storingms.data.value.Location;
import ru.stepanovgzh.wct.storingms.data.value.Pack;
import ru.stepanovgzh.wct.storingms.data.value.Sku;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

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
        apply(new CargoCreatedEvent(
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
    public void handle(ChangeCargoStatusCommand changeCargoStatusCommand)
    {
        apply(new CargoStatusChangedEvent(
            changeCargoStatusCommand.getId(),
            changeCargoStatusCommand.getStatus()));
    }

    @EventSourcingHandler
    public void on(CargoStatusChangedEvent cargoStatusChangedEvent)
    {
        this.status = cargoStatusChangedEvent.getStatus();
    }

    @CommandHandler
    public void handle(UpdateCargoCommand updatecCargoCommand)
    {
        apply(new CargoUpdatedEvent(
            updatecCargoCommand.getId(),
            updatecCargoCommand.getQty()));
    }

    @EventSourcingHandler
    public void on(CargoUpdatedEvent cargoUpdatedEvent)
    {
        this.qty = cargoUpdatedEvent.getQty();
    }

    @CommandHandler
    public void handle(MoveCargoCommand moveCargoCommand)
    {
        apply(new CargoMovedEvent(
            moveCargoCommand.getId(),
            new Location(moveCargoCommand.getZone(), 
                moveCargoCommand.getCell())));
    }

    @EventSourcingHandler
    public void on(CargoMovedEvent cargoMovedEvent)
    {
        this.location = cargoMovedEvent.getLocation();
    }

    @CommandHandler
    public void handle(DeleteCargoCommand deleteCargoCommand)
    {
        apply(new CargoDeletedEvent(
            deleteCargoCommand.getId()));
    }

    @EventSourcingHandler
    public void on(CargoDeletedEvent cargoDeletedEvent)
    {
        AggregateLifecycle.markDeleted();
    }
}
