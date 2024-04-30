package ru.stepanovgzh.wms.productmanagement.cqrs.product.event;

import java.util.UUID;

import lombok.Value;

@Value
public class ProductRemovedEvent 
{
    private UUID productId;
}
