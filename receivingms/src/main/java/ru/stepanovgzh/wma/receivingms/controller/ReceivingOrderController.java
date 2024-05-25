package ru.stepanovgzh.wma.receivingms.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wma.receivingms.cqrs.command.CreateReceivingOrderCommand;
import ru.stepanovgzh.wma.receivingms.data.input.CreateReceivingOrderInput;

@RestController
@RequestMapping("/cargoreceiving")
@RequiredArgsConstructor
public class ReceivingOrderController 
{
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/create")
    public CompletableFuture<UUID> createCargo(
        @RequestBody CreateReceivingOrderInput createReceivingOrderInput)
    {
        return commandGateway.send(new CreateReceivingOrderCommand(
            UUID.randomUUID(),
            createCargoInput.getSkuBarcode(),
            createCargoInput.getSkuName(),
            createCargoInput.getSkuDescription(),
            createCargoInput.getPackType(),
            createCargoInput.getPackDescription(),
            createCargoInput.getQtyOfSku()));
    }   
}
