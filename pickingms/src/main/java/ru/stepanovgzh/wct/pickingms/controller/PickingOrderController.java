package ru.stepanovgzh.wct.pickingms.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.*;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.pickingms.cqrs.command.AddDetailToPickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.CreatePickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.DeletePickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.PickCargoCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.command.RemoveDetailFromPickingOrderCommand;
import ru.stepanovgzh.wct.pickingms.cqrs.event.*;
import ru.stepanovgzh.wct.pickingms.cqrs.query.AllPickingOrdersQuery;
import ru.stepanovgzh.wct.pickingms.data.input.AddDetailToPickingOrderInput;
import ru.stepanovgzh.wct.pickingms.data.input.CreatePickingOrderInput;
import ru.stepanovgzh.wct.pickingms.data.input.DeletePickingOrderInput;
import ru.stepanovgzh.wct.pickingms.data.input.PickCargoInput;
import ru.stepanovgzh.wct.pickingms.data.input.RemoveDetailFromPickingOrderInput;
import ru.stepanovgzh.wct.pickingms.data.view.PickingOrderView;

@RestController
@RequestMapping("/cargopicking")
@RequiredArgsConstructor
public class PickingOrderController 
{
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<UUID> createCargo(
        @RequestBody CreatePickingOrderInput createPickingOrderInput)
    {
        return commandGateway.send(new CreatePickingOrderCommand(
            UUID.randomUUID(),
            createPickingOrderInput.getClientId(),
            createPickingOrderInput.getClientName(),
            createPickingOrderInput.getTransporterId(),
            createPickingOrderInput.getTransporterName(),
            createPickingOrderInput.getPreparationDeadline()));
    }

    @PostMapping("/add_detail")
    public CompletableFuture<UUID> addDetailToPickingOrder(
        @RequestBody AddDetailToPickingOrderInput addDetailToPickingOrderInput)
    {
        return commandGateway.send(new AddDetailToPickingOrderCommand(
            addDetailToPickingOrderInput.getPickingOrderId(),
            UUID.randomUUID(),
            addDetailToPickingOrderInput.getSkuBarcode(),
            addDetailToPickingOrderInput.getSkuName(),
            addDetailToPickingOrderInput.getSkuDecription(),
            addDetailToPickingOrderInput.getQty(),
            addDetailToPickingOrderInput.getPackType(),
            addDetailToPickingOrderInput.getPackDescription()));
    }

    @PostMapping("/remove_detail")
    public CompletableFuture<UUID> removeDetailFromPickingOrder(
        @RequestBody RemoveDetailFromPickingOrderInput removeDetailFromPickingOrderInput)
    {
        return commandGateway.send(new RemoveDetailFromPickingOrderCommand(
            removeDetailFromPickingOrderInput.getPickingOrderId(),
            removeDetailFromPickingOrderInput.getDetailId()));
    }

    @PostMapping("/pick_cargo")
    public CompletableFuture<UUID> pickCargo(@RequestBody PickCargoInput pickCargoInput)
    {
        return commandGateway.send(new PickCargoCommand(
            pickCargoInput.getPickingOrderId(),
            pickCargoInput.getDetailId(),
            pickCargoInput.getCargoId(),
            pickCargoInput.getSkuPickingStatus()));
    }

    @DeleteMapping
    public CompletableFuture<UUID> deletePickingOrder(
        @RequestBody DeletePickingOrderInput deletePickingOrderInput)
    {
        return commandGateway.send(new DeletePickingOrderCommand(
            deletePickingOrderInput.getId()));
    }

    @GetMapping
    public CompletableFuture<List<PickingOrderView>> pickingOrders()
    {
        return queryGateway.query(new AllPickingOrdersQuery(),
            ResponseTypes.multipleInstancesOf(PickingOrderView.class));
    }

    @GetMapping("/{aggregateId}/events")
    public List<EventDTO> listEventsForAggregate(@PathVariable String aggregateId)
    {
        return eventStore.readEvents(aggregateId).asStream()
            .map(event -> new EventDTO(
                event.getPayloadType().getSimpleName(),
                event.getPayload(),
                event.getTimestamp().toString()))
            .collect(Collectors.toList());
    }
}
