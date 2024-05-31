package ru.stepanovgzh.wct.pickingms.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.*;

import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.pickingms.cqrs.command.*;
import ru.stepanovgzh.wct.pickingms.cqrs.event.*;
import ru.stepanovgzh.wct.pickingms.cqrs.query.AllPickingOrdersQuery;
import ru.stepanovgzh.wct.pickingms.data.input.*;
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
        @Valid @RequestBody CreatePickingOrderInput createPickingOrderInput)
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
        @Valid @RequestBody AddDetailToPickingOrderInput addDetailToPickingOrderInput)
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
        @Valid @RequestBody RemoveDetailFromPickingOrderInput removeDetailFromPickingOrderInput)
    {
        return commandGateway.send(new RemoveDetailFromPickingOrderCommand(
            removeDetailFromPickingOrderInput.getPickingOrderId(),
            removeDetailFromPickingOrderInput.getDetailId()));
    }

    @PostMapping("/change_status")
    public CompletableFuture<UUID> changeStatusOfPickingOrder(
        @Valid @RequestBody ChangeStatusOfPickingOrderInput changeStatusOfPickingOrderInput)
    {
        return commandGateway.send(new ChangeStatusOfPickingOrderCommand(
            changeStatusOfPickingOrderInput.getId(),
            changeStatusOfPickingOrderInput.getStatus()));
    }

    @PostMapping("/pick_cargo")
    public CompletableFuture<UUID> pickCargo(@Valid @RequestBody PickCargoInput pickCargoInput)
    {
        return commandGateway.send(new PickCargoCommand(
            pickCargoInput.getPickingOrderId(),
            pickCargoInput.getDetailId(),
            pickCargoInput.getCargoId(),
            pickCargoInput.getSkuPickingStatus()));
    }

    @DeleteMapping
    public CompletableFuture<UUID> deletePickingOrder(
        @Valid @RequestBody DeletePickingOrderInput deletePickingOrderInput)
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
