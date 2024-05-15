package ru.stepanovgzh.wma.receivingms.aggregate;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class ReceivingOrderAggregate 
{
    @TargetAggregateIdentifier
    UUID id;

}
