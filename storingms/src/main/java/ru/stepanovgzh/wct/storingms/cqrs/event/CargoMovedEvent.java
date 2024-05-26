package ru.stepanovgzh.wct.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.Location;

@Value
public class CargoMovedEvent 
{
    UUID id;
    Location location;
}
