package ru.stepanovgzh.wct.orderingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.value.SkuPickingStatus;

@Value
public class PickCargoCommand 
{
    @TargetAggregateIdentifier
    UUID pickingOrderId;
    UUID detailId;
    UUID cargoId;
    SkuPickingStatus skuPickingStatus;
}
