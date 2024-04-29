package ru.stepanovgzh.wms.productmanagement.domain.commands.product;

import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;

import lombok.Value;

@Value
public class AddProductCommand 
{
    @AggregateIdentifier
    private UUID productId;
    private String barcode;
    private String name;
    private String description;
}
