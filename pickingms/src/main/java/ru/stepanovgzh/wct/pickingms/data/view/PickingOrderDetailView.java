package ru.stepanovgzh.wct.pickingms.data.view;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.pickingms.data.value.Pack;
import ru.stepanovgzh.wct.pickingms.data.value.Sku;
import ru.stepanovgzh.wct.pickingms.data.value.SkuPickingStatus;

@Value
public class PickingOrderDetailView 
{
    UUID id;
    UUID pickingOrderId;
    Sku sku;
    int qty;
    Pack pack;
    UUID pickingCargoId;
    SkuPickingStatus skuPickingStatus;
}
