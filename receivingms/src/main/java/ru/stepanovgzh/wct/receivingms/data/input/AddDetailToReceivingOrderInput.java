package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.PackType;

@Value
public class AddDetailToReceivingOrderInput 
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
