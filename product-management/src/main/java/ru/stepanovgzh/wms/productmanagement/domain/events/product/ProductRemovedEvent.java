package ru.stepanovgzh.wms.productmanagement.domain.events.product;

import java.util.UUID;

import lombok.Value;

@Value
public class ProductRemovedEvent 
{
    private UUID productId;
}
