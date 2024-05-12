package ru.stepanovgzh.wma.storingms.data.view;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wma.storingms.data.value.CargoStatus;
import ru.stepanovgzh.wma.storingms.data.value.PackType;

@Value
public class CargoView 
{
    UUID cargoId;
    String skuBarcode;
    String skuName;
    String skuDescription;
    PackType packType;
    String packDescription;
    int qtyOfSku;
    String zoneName;
    String cellName;
    CargoStatus cargoStatus;
}
