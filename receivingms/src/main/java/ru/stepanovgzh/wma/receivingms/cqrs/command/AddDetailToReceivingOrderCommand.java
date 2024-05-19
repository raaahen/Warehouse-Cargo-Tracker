package ru.stepanovgzh.wma.receivingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wma.receivingms.data.value.PackType;

@Value
public class AddDetailToReceivingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
    String skuBarcode;
    String skuName;
    String skuDecription;
    int qty;
    PackType packType;
}
