package ru.stepanovgzh.wms.productmanagement.domain.events.product;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Barcode;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Description;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Name;

@Value
public class ProductAddedEvent 
{
    private UUID productId;
    private Barcode barcode;
    private Name name;
    private Description description;
}
