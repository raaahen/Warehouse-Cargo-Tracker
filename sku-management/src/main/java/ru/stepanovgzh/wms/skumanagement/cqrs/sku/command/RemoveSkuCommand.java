package ru.stepanovgzh.wms.skumanagement.cqrs.sku.command;

import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;

import lombok.Value;

@Value
public class RemoveSkuCommand 
{
    @AggregateIdentifier
    private UUID skuId;
}
