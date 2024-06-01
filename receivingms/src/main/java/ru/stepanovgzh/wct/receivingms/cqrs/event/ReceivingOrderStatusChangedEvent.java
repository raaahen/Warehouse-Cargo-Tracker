package ru.stepanovgzh.wct.receivingms.cqrs.event;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;

import java.util.UUID;

@Value
public class ReceivingOrderStatusChangedEvent
{
    UUID id;
    ReceivingStatus status;
}
