package ru.stepanovgzh.wct.pickingms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ru.stepanovgzh.wct.pickingms.cqrs.event.PickingOrderCreatedEvent;
import ru.stepanovgzh.wct.pickingms.data.model.PickingOrder;
import ru.stepanovgzh.wct.pickingms.data.view.PickingOrderView;

@Mapper(componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PickingOrderMapper 
{
    PickingOrder map(PickingOrderCreatedEvent pickingOrderCreatedEvent);
    PickingOrderView map(PickingOrder receivingOrder);
}
