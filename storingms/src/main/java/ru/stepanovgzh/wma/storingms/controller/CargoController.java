package ru.stepanovgzh.wma.storingms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wma.storingms.cqrs.command.CreateCargoCommand;
import ru.stepanovgzh.wma.storingms.cqrs.command.MoveCargoCommand;
import ru.stepanovgzh.wma.storingms.cqrs.command.ChangeCargoStatusCommand;
import ru.stepanovgzh.wma.storingms.cqrs.command.UpdateCargoCommand;
import ru.stepanovgzh.wma.storingms.cqrs.query.AllCargoQuery;
import ru.stepanovgzh.wma.storingms.cqrs.command.DeleteCargoCommand;
import ru.stepanovgzh.wma.storingms.data.input.CreateCargoInput;
import ru.stepanovgzh.wma.storingms.data.input.MoveCargoInput;
import ru.stepanovgzh.wma.storingms.data.input.ChangeCargoStatusInput;
import ru.stepanovgzh.wma.storingms.data.input.UpdateCargoInput;
import ru.stepanovgzh.wma.storingms.data.value.PackType;
import ru.stepanovgzh.wma.storingms.data.view.CargoView;
import ru.stepanovgzh.wma.storingms.data.input.DeleteCargoInput;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/cargo")
@RequiredArgsConstructor
public class CargoController 
{
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/create")
    public CompletableFuture<UUID> createCargo(@RequestBody CreateCargoInput createCargoInput)
    {
        return commandGateway.send(new CreateCargoCommand(
            UUID.randomUUID(),
            createCargoInput.getSkuBarcode(),
            createCargoInput.getSkuName(),
            createCargoInput.getSkuDescription(),
            createCargoInput.getPackType(),
            createCargoInput.getPackDescription(),
            createCargoInput.getQtyOfSku()));
    }

    @PostMapping("/move")
    public CompletableFuture<UUID> moveCargo(@RequestBody MoveCargoInput moveCargoInput)
    {
        return commandGateway.send(new MoveCargoCommand(
            UUID.randomUUID(),
            moveCargoInput.getZoneName(),
            moveCargoInput.getCellName()));
    }

    @PostMapping("/changestatus")
    public CompletableFuture<UUID> changeCargoStatus(
        @RequestBody ChangeCargoStatusInput changeCargoStatusInput)
    {
        return commandGateway.send(new ChangeCargoStatusCommand(
            UUID.randomUUID(),
            changeCargoStatusInput.getStatus()));
    }

    @PostMapping("/update")
    public CompletableFuture<UUID> updateCargo(@RequestBody UpdateCargoInput updateCargoInput)
    {
        return commandGateway.send(new UpdateCargoCommand(
            UUID.randomUUID(),
            updateCargoInput.getQty()));
    }

    @PostMapping("/delete")
    public CompletableFuture<UUID> deleteCargo(@RequestBody DeleteCargoInput deleteCargoInput)
    {
        return commandGateway.send(new DeleteCargoCommand(
            deleteCargoInput.getId()));
    }

    @GetMapping()
    public CompletableFuture<List<CargoView>> cargoList()
    {
        return queryGateway.query(new AllCargoQuery(),
            ResponseTypes.multipleInstancesOf(CargoView.class));
    }
}
