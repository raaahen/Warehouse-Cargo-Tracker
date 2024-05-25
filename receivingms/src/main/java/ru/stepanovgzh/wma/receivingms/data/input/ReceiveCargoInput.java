package ru.stepanovgzh.wma.receivingms.data.input;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wma.receivingms.data.value.PackType;

@Value
public class ReceiveCargoInput 
{
    UUID receivingOrderId;
    UUID detailId;
    String skuBarcode;
    String skuName;
    String skuDecription;
    int qty;
    PackType packType;
    String packDescription;
}
