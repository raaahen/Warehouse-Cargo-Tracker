package ru.stepanovgzh.wct.pickingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.pickingms.data.value.PackType;

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
