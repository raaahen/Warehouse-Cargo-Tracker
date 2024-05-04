package ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Description;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Name;

@Value
public class SkuGroupInfoUpdatedEvent 
{
    private UUID skuGroupId;
    private Name name;
    private Description description;
}
