package ru.stepanovgzh.wct.pickingms.cqrs.command;

import lombok.*;
import org.axonframework.modelling.command.*;
import ru.stepanovgzh.wct.pickingms.data.value.*;

import java.util.*;

@Value
public class ChangeStatusOfPickingOrderCommand
{
    @TargetAggregateIdentifier
    UUID id;
    PickingStatus status;
}
