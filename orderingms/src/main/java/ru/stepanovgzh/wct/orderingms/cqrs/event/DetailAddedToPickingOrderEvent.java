package ru.stepanovgzh.wct.orderingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.entity.PickingOrderDetail;

@Value
public class DetailAddedToPickingOrderEvent 
{
    @TargetAggregateIdentifier
    UUID pickingOrderId;
    PickingOrderDetail detail;
}
