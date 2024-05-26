package ru.stepanovgzh.wct.receivingms.data.view;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.Pack;
import ru.stepanovgzh.wct.receivingms.data.value.Sku;
import ru.stepanovgzh.wct.receivingms.data.value.SkuReceivingStatus;

@Value
public class ReceivingOrderDetailView 
{
    UUID id;
    UUID receivingOrderId;
    Sku sku;
    int qty;
    Pack pack;
    UUID receivedCargoId;
    SkuReceivingStatus skuReceivingStatus;
}
