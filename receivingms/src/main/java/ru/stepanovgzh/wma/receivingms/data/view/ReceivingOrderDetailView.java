package ru.stepanovgzh.wma.receivingms.data.view;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wma.receivingms.data.value.Pack;
import ru.stepanovgzh.wma.receivingms.data.value.Sku;
import ru.stepanovgzh.wma.receivingms.data.value.SkuReceivingStatus;

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
