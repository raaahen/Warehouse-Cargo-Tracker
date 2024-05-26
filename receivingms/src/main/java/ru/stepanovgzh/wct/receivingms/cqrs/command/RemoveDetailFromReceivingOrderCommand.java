package ru.stepanovgzh.wct.receivingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class RemoveDetailFromReceivingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
}
