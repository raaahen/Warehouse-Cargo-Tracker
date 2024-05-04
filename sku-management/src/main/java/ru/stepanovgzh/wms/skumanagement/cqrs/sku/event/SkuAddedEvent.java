package ru.stepanovgzh.wms.skumanagement.cqrs.sku.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Barcode;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Description;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Name;

@Value
public class SkuAddedEvent 
{
    private UUID skuId;
    private Barcode barcode;
    private Name name;
    private Description description;
}
