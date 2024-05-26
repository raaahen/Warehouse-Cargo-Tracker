package ru.stepanovgzh.wct.orderingms.cqrs.command;

import java.util.Date;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class CreatePickingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID id;
    UUID clientId;
    String clientName;
    UUID transporterId;
    String transporterName;
    Date preparationDeadline;
}
