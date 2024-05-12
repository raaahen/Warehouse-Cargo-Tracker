package ru.stepanovgzh.wma.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wma.storingms.data.value.CargoStatus;

@Value
public class CargoStatusChangedEvent 
{
    UUID id;
    CargoStatus status;
}
