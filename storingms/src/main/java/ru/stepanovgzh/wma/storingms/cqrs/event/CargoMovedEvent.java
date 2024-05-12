package ru.stepanovgzh.wma.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wma.storingms.data.value.Location;

@Value
public class CargoMovedEvent 
{
    UUID id;
    Location location;
}
