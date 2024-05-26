package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.SkuReceivingStatus;

@Value
public class ReceiveCargoInput 
{
    UUID receivingOrderId;
    UUID detailId;
    UUID cargoId;
    SkuReceivingStatus skuReceivingStatus;
}
