package ru.stepanovgzh.wct.storingms.data.input;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.PackType;

@Value
public class CreateCargoInput 
{
    private UUID id;
    String skuBarcode;
    String skuName;
    String skuDescription;
    PackType packType;
    String packDescription;
    int qtyOfSku;
}
