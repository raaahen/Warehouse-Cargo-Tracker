package ru.stepanovgzh.wms.skumanagement.cqrs.sku.event;

import java.util.UUID;

import lombok.Value;

@Value
public class SkuRemovedEvent 
{
    private UUID skuId;
}
