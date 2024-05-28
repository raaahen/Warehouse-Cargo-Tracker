package ru.stepanovgzh.wct.storingms.data.view;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.CargoStatus;
import ru.stepanovgzh.wct.storingms.data.value.Location;
import ru.stepanovgzh.wct.storingms.data.value.Pack;
import ru.stepanovgzh.wct.storingms.data.value.Sku;

@Value
public class CargoView 
{
    UUID id;
    Sku sku;
    Pack pack;
    int qty;
    Location location;
    CargoStatus status;
}
