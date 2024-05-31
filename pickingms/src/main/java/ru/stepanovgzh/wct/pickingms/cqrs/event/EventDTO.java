package ru.stepanovgzh.wct.pickingms.cqrs.event;

import lombok.*;

@Value
public class EventDTO
{
    String eventType;
    Object payload;
    String timestamp;
}
