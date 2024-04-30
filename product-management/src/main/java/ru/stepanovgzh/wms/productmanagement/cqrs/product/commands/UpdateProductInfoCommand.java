package ru.stepanovgzh.wms.productmanagement.cqrs.product.commands;

import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;

import lombok.Value;

@Value
public class UpdateProductInfoCommand
{
    @AggregateIdentifier
    private UUID productId;
    private String barcode;
    private String name;
    private String description;
}
