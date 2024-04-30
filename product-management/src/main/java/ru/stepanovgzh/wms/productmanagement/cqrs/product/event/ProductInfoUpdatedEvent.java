package ru.stepanovgzh.wms.productmanagement.cqrs.product.event;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wms.productmanagement.data.model.types.Barcode;
import ru.stepanovgzh.wms.productmanagement.data.model.types.Description;
import ru.stepanovgzh.wms.productmanagement.data.model.types.Name;

@Value
public class ProductInfoUpdatedEvent 
{
    private UUID productId;
    private Barcode barcode;
    private Name name;
    private Description description;
}
