package ru.stepanovgzh.wct.pickingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.pickingms.data.value.SkuPickingStatus;

@Value
public class PickCargoCommand 
{
    @TargetAggregateIdentifier
    UUID pickingOrderId;
    UUID detailId;
    UUID cargoId;
    SkuPickingStatus skuPickingStatus;
}
