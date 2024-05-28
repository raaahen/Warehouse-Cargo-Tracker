package ru.stepanovgzh.wct.storingms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.storingms.cqrs.command.ChangeCargoStatusCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.CreateCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.DeleteCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.MoveCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.command.UpdateCargoCommand;
import ru.stepanovgzh.wct.storingms.cqrs.query.AllCargoQuery;
import ru.stepanovgzh.wct.storingms.data.input.ChangeCargoStatusInput;
import ru.stepanovgzh.wct.storingms.data.input.CreateCargoInput;
import ru.stepanovgzh.wct.storingms.data.input.DeleteCargoInput;
import ru.stepanovgzh.wct.storingms.data.input.MoveCargoInput;
import ru.stepanovgzh.wct.storingms.data.input.UpdateCargoInput;
import ru.stepanovgzh.wct.storingms.data.view.CargoView;

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

@RestController
@RequestMapping("/cargostoring")
@RequiredArgsConstructor
public class CargoController 
{
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/create")
    public CompletableFuture<UUID> createCargo(
        @Valid @RequestBody CreateCargoInput createCargoInput)
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
    public CompletableFuture<UUID> moveCargo(
        @Valid @RequestBody MoveCargoInput moveCargoInput)
    {
        return commandGateway.send(new MoveCargoCommand(
            moveCargoInput.getId(),
            moveCargoInput.getZone(),
            moveCargoInput.getCell()));
    }

    @PostMapping("/changestatus")
    public CompletableFuture<UUID> changeCargoStatus(
        @RequestBody ChangeCargoStatusInput changeCargoStatusInput)
    {
        return commandGateway.send(new ChangeCargoStatusCommand(
            changeCargoStatusInput.getId(),
            changeCargoStatusInput.getStatus()));
    }

    @PostMapping("/update")
    public CompletableFuture<UUID> updateCargo(
        @Valid @RequestBody UpdateCargoInput updateCargoInput)
    {
        return commandGateway.send(new UpdateCargoCommand(
            updateCargoInput.getId(),
            updateCargoInput.getQty()));
    }

    @DeleteMapping
    public CompletableFuture<UUID> deleteCargo(
        @Valid @RequestBody DeleteCargoInput deleteCargoInput)
    {
        return commandGateway.send(new DeleteCargoCommand(
            deleteCargoInput.getId()));
    }

    @GetMapping
    public CompletableFuture<List<CargoView>> cargoList()
    {
        return queryGateway.query(new AllCargoQuery(),
            ResponseTypes.multipleInstancesOf(CargoView.class));
    }
}
