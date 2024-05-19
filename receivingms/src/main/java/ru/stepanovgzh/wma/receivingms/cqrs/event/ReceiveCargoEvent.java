package ru.stepanovgzh.wma.receivingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wma.receivingms.data.value.PackType;
import ru.stepanovgzh.wma.receivingms.data.value.Sku;

@Value
public class ReceiveCargoEvent 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
    Sku sku;
    int qty;
    PackType packType;
}
