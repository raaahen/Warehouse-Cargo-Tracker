package ru.stepanovgzh.wct.orderingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.value.PackType;

@Value
public class AddDetailToPickingOrderCommand 
{
    @TargetAggregateIdentifier
    UUID pickingOrderId;
    UUID detailId;
    String skuBarcode;
    String skuName;
    String skuDescription;
    int qty;
    PackType packType;
    String packDescription;
}
