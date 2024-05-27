package ru.stepanovgzh.wct.orderingms.data.input;

import java.util.UUID;

import lombok.Value;

@Value
public class RemoveDetailFromPickingOrderInput 
{
    UUID pickingOrderId;
    UUID detailId;
}
