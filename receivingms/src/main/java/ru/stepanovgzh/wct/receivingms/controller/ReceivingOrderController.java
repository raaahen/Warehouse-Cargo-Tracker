package ru.stepanovgzh.wct.receivingms.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.receivingms.cqrs.command.AddDetailToReceivingOrderCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.command.CreateReceivingOrderCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.command.DeleteReceivingOrderCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.command.ReceiveCargoCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.command.RemoveDetailFromReceivingOrderCommand;
import ru.stepanovgzh.wct.receivingms.cqrs.query.AllReceivingOrdersQuery;
import ru.stepanovgzh.wct.receivingms.data.input.AddDetailToReceivingOrderInput;
import ru.stepanovgzh.wct.receivingms.data.input.CreateReceivingOrderInput;
import ru.stepanovgzh.wct.receivingms.data.input.DeleteReceivingOrderInput;
import ru.stepanovgzh.wct.receivingms.data.input.ReceiveCargoInput;
import ru.stepanovgzh.wct.receivingms.data.input.RemoveDetailFromReceivingOrderInput;
import ru.stepanovgzh.wct.receivingms.data.view.ReceivingOrderView;

@RestController
@RequestMapping("/cargoreceiving")
@RequiredArgsConstructor
public class ReceivingOrderController 
{
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/create")
    public CompletableFuture<UUID> createReceivingOrder(
        @RequestBody CreateReceivingOrderInput createReceivingOrderInput)
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
        @RequestBody AddDetailToReceivingOrderInput addDetailToReceivingOrderInput)
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
    public CompletableFuture<UUID> removeDetailToReceivingOrder(
        @RequestBody RemoveDetailFromReceivingOrderInput removeDetailFromReceivingOrderInput)
    {
        return commandGateway.send(new RemoveDetailFromReceivingOrderCommand(
            removeDetailFromReceivingOrderInput.getReceivingOrderId(),
            removeDetailFromReceivingOrderInput.getDetailId()));
    }

    @PostMapping("/receive_cargo")
    public CompletableFuture<UUID> receiveCargo(@RequestBody ReceiveCargoInput receiveCargoInput)
    {
        return commandGateway.send(new ReceiveCargoCommand(
            receiveCargoInput.getReceivingOrderId(),
            receiveCargoInput.getDetailId(),
            receiveCargoInput.getCargoId(),
            receiveCargoInput.getSkuReceivingStatus()));
    }

    @DeleteMapping
    public CompletableFuture<UUID> deleteReceivingOrder(
        @RequestBody DeleteReceivingOrderInput deleteReceivingOrderInput)
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
}
