package ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event;

import java.util.UUID;

import lombok.Value;

@Value
public class SkuGroupRemovedEvent 
{
    private UUID skuGroupId;
}
