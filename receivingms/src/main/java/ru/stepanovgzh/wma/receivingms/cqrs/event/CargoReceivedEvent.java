package ru.stepanovgzh.wma.receivingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class CargoReceivedEvent 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
    UUID receivedCargoId;
}
