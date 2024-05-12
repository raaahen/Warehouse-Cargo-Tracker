package ru.stepanovgzh.wma.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;

@Value
public class CargoUpdatedEvent 
{
    UUID id;
    int qty;
}
