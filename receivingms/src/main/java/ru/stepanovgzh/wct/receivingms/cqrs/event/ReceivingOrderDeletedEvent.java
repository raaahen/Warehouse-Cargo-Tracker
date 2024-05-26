package ru.stepanovgzh.wct.receivingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class ReceivingOrderDeletedEvent 
{
    @TargetAggregateIdentifier
    UUID id;
}
