package ru.stepanovgzh.wct.receivingms.controller;

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
import ru.stepanovgzh.wct.receivingms.cqrs.command.*;
import ru.stepanovgzh.wct.receivingms.cqrs.event.*;
import ru.stepanovgzh.wct.receivingms.cqrs.query.AllReceivingOrdersQuery;
import ru.stepanovgzh.wct.receivingms.data.input.*;
import ru.stepanovgzh.wct.receivingms.data.view.ReceivingOrderView;

@RestController
@RequestMapping("/cargoreceiving")
@RequiredArgsConstructor
public class ReceivingOrderController 
{
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<UUID> createReceivingOrder(
        @Valid @RequestBody CreateReceivingOrderInput createReceivingOrderInput)
    {
        return commandGateway.send(new CreateReceivingOrderCommand(
            UUID.randomUUID(),
            createReceivingOrderInput.getSupplierId(),
            createReceivingOrderInput.getSupplierName(),
            createReceivingOrderInput.getTransporterId(),
            createReceivingOrderInput.getTransporterName(),
            createReceivingOrderInput.getDate()));
    }

    @PostMapping("/add_detail")
    public CompletableFuture<UUID> addDetailToReceivingOrder(
        @Valid @RequestBody AddDetailToReceivingOrderInput addDetailToReceivingOrderInput)
    {
        return commandGateway.send(new AddDetailToReceivingOrderCommand(
            addDetailToReceivingOrderInput.getReceivingOrderId(),
            UUID.randomUUID(),
            addDetailToReceivingOrderInput.getSkuBarcode(),
            addDetailToReceivingOrderInput.getSkuName(),
            addDetailToReceivingOrderInput.getSkuDecription(),
            addDetailToReceivingOrderInput.getQty(),
            addDetailToReceivingOrderInput.getPackType(),
            addDetailToReceivingOrderInput.getPackDescription()));
    }

    @PostMapping("/remove_detail")
    public CompletableFuture<UUID> removeDetailFromReceivingOrder(
        @Valid @RequestBody RemoveDetailFromReceivingOrderInput removeDetailFromReceivingOrderInput)
    {
        return commandGateway.send(new RemoveDetailFromReceivingOrderCommand(
            removeDetailFromReceivingOrderInput.getReceivingOrderId(),
            removeDetailFromReceivingOrderInput.getDetailId()));
    }

    @PostMapping("/change_status")
    public CompletableFuture<UUID> changeStatusOfReceivingOrder(
        @Valid @RequestBody ChangeStatusOfReceivingOrderInput changeStatusOfReceivingOrderInput)
    {
        return commandGateway.send(new ChangeStatusOfReceivingOrderCommand(
            changeStatusOfReceivingOrderInput.getId(),
            changeStatusOfReceivingOrderInput.getStatus()));
    }

    @PostMapping("/receive_cargo")
    public CompletableFuture<UUID> receiveCargo(@Valid @RequestBody ReceiveCargoInput receiveCargoInput)
    {
        return commandGateway.send(new ReceiveCargoCommand(
            receiveCargoInput.getReceivingOrderId(),
            receiveCargoInput.getDetailId(),
            receiveCargoInput.getCargoId(),
            receiveCargoInput.getSkuReceivingStatus()));
    }

    @DeleteMapping
    public CompletableFuture<UUID> deleteReceivingOrder(
        @Valid @RequestBody DeleteReceivingOrderInput deleteReceivingOrderInput)
    {
        return commandGateway.send(new DeleteReceivingOrderCommand(
            deleteReceivingOrderInput.getId()));
    }

    @GetMapping
    public CompletableFuture<List<ReceivingOrderView>> receivingOrders()
    {
        return queryGateway.query(new AllReceivingOrdersQuery(),
            ResponseTypes.multipleInstancesOf(ReceivingOrderView.class));
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
