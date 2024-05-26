package ru.stepanovgzh.wct.storingms.cqrs.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.Pack;
import ru.stepanovgzh.wct.storingms.data.value.Sku;

@Value
public class CargoCreatedEvent 
{
    private UUID id;
    Sku sku;
    Pack pack;
    int qty;
}
