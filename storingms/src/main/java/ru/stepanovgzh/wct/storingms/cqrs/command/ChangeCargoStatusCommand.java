package ru.stepanovgzh.wct.storingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.CargoStatus;

@Value
public class ChangeCargoStatusCommand 
{
    @TargetAggregateIdentifier
    UUID id;
    CargoStatus status;
}
