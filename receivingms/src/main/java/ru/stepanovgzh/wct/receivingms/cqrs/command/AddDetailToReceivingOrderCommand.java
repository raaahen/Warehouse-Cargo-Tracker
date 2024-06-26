package ru.stepanovgzh.wct.receivingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.PackType;

@Value
public class AddDetailToReceivingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
    String skuBarcode;
    String skuName;
    String skuDescription;
    int qty;
    PackType packType;
    String packDescription;
}
