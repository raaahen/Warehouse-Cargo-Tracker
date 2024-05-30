package ru.stepanovgzh.wct.pickingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.pickingms.data.value.SkuPickingStatus;

@Value
public class CargoPickedEvent
{
    @TargetAggregateIdentifier
    UUID pickingOrderId;
    UUID detailId;
    UUID pickedCargoId;
    SkuPickingStatus skuPickingStatus;
}
