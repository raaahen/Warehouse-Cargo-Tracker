package ru.stepanovgzh.wct.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.CargoStatus;

@Value
public class CargoStatusChangedEvent 
{
    UUID id;
    CargoStatus status;
}
