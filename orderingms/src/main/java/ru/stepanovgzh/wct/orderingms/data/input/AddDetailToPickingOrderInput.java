package ru.stepanovgzh.wct.orderingms.data.input;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.value.PackType;

@Value
public class AddDetailToPickingOrderInput 
{
    UUID pickingOrderId;
    String skuBarcode;
    String skuName;
    String skuDecription;
    int qty;
    PackType packType;
    String packDescription;
}
