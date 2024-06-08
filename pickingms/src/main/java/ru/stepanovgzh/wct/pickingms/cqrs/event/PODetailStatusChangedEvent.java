package ru.stepanovgzh.wct.pickingms.cqrs.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ru.stepanovgzh.wct.pickingms.data.value.PickingStatus;

import java.util.UUID;

@Value
public class ChangeStatusOfPODetailCommand
{
    @TargetAggregateIdentifier
    UUID id;
    UUID pickingStatusId;
    PickingStatus status;
}
