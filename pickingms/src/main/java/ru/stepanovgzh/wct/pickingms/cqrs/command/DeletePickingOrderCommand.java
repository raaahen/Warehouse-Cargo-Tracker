package ru.stepanovgzh.wct.pickingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class DeletePickingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID id;
}
