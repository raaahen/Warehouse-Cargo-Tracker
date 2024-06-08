package ru.stepanovgzh.wct.receivingms.cqrs.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;

import java.util.UUID;

@Value
public class ChangeStatusOfReceivingOrderCommand
{
    @TargetAggregateIdentifier
    UUID id;
    ReceivingStatus status;
}
