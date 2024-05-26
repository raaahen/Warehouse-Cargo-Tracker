package ru.stepanovgzh.wct.orderingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class DetailRemovedFromPickingOrderEvent 
{
    @TargetAggregateIdentifier
    UUID pickingOrderId;
    UUID detailId;
}
