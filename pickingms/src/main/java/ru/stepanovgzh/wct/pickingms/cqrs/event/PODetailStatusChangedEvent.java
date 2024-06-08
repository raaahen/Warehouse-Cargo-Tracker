package ru.stepanovgzh.wct.pickingms.cqrs.event;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ru.stepanovgzh.wct.pickingms.data.value.PickingStatus;
import ru.stepanovgzh.wct.pickingms.data.value.SkuPickingStatus;

import java.util.UUID;

@Value
public class PODetailStatusChangedEvent
{
    @TargetAggregateIdentifier
    UUID id;
    UUID detailId;
    SkuPickingStatus status;
}
