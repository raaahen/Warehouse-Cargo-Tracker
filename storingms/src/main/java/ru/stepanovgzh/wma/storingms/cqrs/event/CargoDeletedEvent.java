package ru.stepanovgzh.wma.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;

@Value
public class CargoDeletedEvent 
{
    UUID id;
}
