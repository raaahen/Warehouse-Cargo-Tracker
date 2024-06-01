package ru.stepanovgzh.wct.receivingms.cqrs.command;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;

import java.util.UUID;

@Value
public class ChangeStatusOfReceivingOrderCommand
{
    UUID id;
    ReceivingStatus status;
}
