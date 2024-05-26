package ru.stepanovgzh.wct.storingms.data.view;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.CargoStatus;
import ru.stepanovgzh.wct.storingms.data.value.PackType;

@Value
public class CargoView 
{
    UUID id;
    String skuBarcode;
    String skuName;
    String skuDescription;
    PackType packType;
    String packDescription;
    int qtyOfSku;
    String zone;
    String cell;
    CargoStatus cargoStatus;
}
