package ru.stepanovgzh.wma.receivingms.data.input;

import java.util.UUID;

import lombok.Value;

@Value
public class RemoveDetailFromReceivingOrderInput 
{
    UUID receivingOrderId;
    UUID detailId;
}
