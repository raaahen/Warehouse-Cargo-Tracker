package ru.stepanovgzh.wct.receivingms.cqrs.command;

import java.util.Date;
import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class CreateReceivingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID id;
    UUID supplierId;
    String supplierName;
    UUID transporterId;
    String transporterName;
    Date date;
}
