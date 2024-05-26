package ru.stepanovgzh.wct.orderingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class PickingOrderDeletedEvent 
{
    @TargetAggregateIdentifier
    UUID id;
}
