package ru.stepanovgzh.wct.orderingms.aggregate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import ru.stepanovgzh.wct.orderingms.data.entity.Client;
import ru.stepanovgzh.wct.orderingms.data.entity.PickingOrderDetail;
import ru.stepanovgzh.wct.orderingms.data.entity.Transporter;
import ru.stepanovgzh.wct.orderingms.data.value.SkuPickingStatus;

@Aggregate
@NoArgsConstructor
public class PickingOrderAggregate 
{
    @TargetAggregateIdentifier
    UUID id;
    Client client;
    Transporter Transporter;
    Date preparationDeadline;
    SkuPickingStatus status;
    List<PickingOrderDetail> details;
}

