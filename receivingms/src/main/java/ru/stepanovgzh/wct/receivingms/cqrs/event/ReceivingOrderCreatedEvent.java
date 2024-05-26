package ru.stepanovgzh.wct.receivingms.cqrs.event;

import java.util.Date;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.entity.Supplier;
import ru.stepanovgzh.wct.receivingms.data.entity.Transporter;

@Value
public class ReceivingOrderCreatedEvent 
{
    @TargetAggregateIdentifier
    UUID id;
    Supplier supplier;
    Transporter transporter;
    Date date;
}
