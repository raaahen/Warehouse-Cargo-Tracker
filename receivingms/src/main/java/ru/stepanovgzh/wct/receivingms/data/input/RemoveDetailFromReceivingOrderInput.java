package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.UUID;

import lombok.Value;

@Value
public class RemoveDetailFromReceivingOrderInput 
{
    UUID receivingOrderId;
    UUID detailId;
}
