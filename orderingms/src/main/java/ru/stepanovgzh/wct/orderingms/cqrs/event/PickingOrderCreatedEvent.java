package ru.stepanovgzh.wct.orderingms.cqrs.event;

import java.util.Date;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.entity.Client;
import ru.stepanovgzh.wct.orderingms.data.entity.Transporter;

@Value
public class PickingOrderCreatedEvent 
{
    @TargetAggregateIdentifier
    UUID id;
    Client client;
    Transporter transporter;
    Date preparationDeadline;
}
