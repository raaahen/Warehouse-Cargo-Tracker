package ru.stepanovgzh.wma.storingms.data.input;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wma.storingms.data.value.CargoStatus;

@Value
public class ChangeCargoStatusInput 
{
    @TargetAggregateIdentifier
    UUID id;
    CargoStatus status;
}
