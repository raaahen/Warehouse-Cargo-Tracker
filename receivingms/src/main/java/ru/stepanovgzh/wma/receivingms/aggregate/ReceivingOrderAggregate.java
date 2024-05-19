package ru.stepanovgzh.wma.receivingms.aggregate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import ru.stepanovgzh.wma.receivingms.data.entity.ReceivingOrderDetail;
import ru.stepanovgzh.wma.receivingms.data.entity.Supplier;
import ru.stepanovgzh.wma.receivingms.data.entity.Transporter;
import ru.stepanovgzh.wma.receivingms.data.value.ReceivingStatus;

@Aggregate
@NoArgsConstructor
public class ReceivingOrderAggregate 
{
    @TargetAggregateIdentifier
    UUID id;
    Supplier supplier;
    Transporter transporter;
    Date date;
    ReceivingStatus status;
    List<ReceivingOrderDetail> details;
}
