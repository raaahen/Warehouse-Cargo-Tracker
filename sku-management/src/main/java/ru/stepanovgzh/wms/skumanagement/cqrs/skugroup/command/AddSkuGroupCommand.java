package ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.command;

import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;

import lombok.Value;

@Value
public class AddSkuGroupCommand 
{
    @AggregateIdentifier
    private UUID skuGroupId;
    private String name;
    private String description;
}
