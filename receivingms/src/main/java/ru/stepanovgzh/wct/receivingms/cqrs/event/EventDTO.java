package ru.stepanovgzh.wct.receivingms.cqrs.event;

import lombok.*;

@Value
public class EventDTO
{
    String eventType;
    Object payload;
    String timestamp;
}
