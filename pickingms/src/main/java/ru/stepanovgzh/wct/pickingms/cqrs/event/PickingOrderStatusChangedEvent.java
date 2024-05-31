package ru.stepanovgzh.wct.pickingms.cqrs.event;

import lombok.*;
import org.axonframework.modelling.command.*;
import ru.stepanovgzh.wct.pickingms.data.value.*;

import java.util.*;

@Value
public class PickingOrderStatusChangedEvent
{
    @TargetAggregateIdentifier
    UUID id;
    PickingStatus status;
}
