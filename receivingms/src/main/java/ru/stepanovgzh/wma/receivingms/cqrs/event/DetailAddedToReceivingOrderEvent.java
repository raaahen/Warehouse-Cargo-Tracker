package ru.stepanovgzh.wma.receivingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wma.receivingms.data.entity.ReceivingOrderDetail;

@Value
public class DetailAddedToReceivingOrderEvent 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    ReceivingOrderDetail detail;
}
