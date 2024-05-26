package ru.stepanovgzh.wct.orderingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class RemoveDetailFromPickingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID pickingOrderId;
    UUID detailId;
}
