package ru.stepanovgzh.wct.storingms.cqrs.event;

import lombok.*;

@Value
public class EventDTO
{
    String eventType;
    Object payload;
    String timestamp;
}
