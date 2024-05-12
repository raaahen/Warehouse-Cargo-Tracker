package ru.stepanovgzh.wma.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wma.storingms.data.value.Pack;
import ru.stepanovgzh.wma.storingms.data.value.Sku;

@Value
public class CargoCreatedEvent 
{
    private UUID id;
    Sku sku;
    Pack pack;
    int qty;
}
