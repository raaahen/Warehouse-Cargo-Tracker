package ru.stepanovgzh.wct.orderingms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ru.stepanovgzh.wct.orderingms.cqrs.event.PickingOrderCreatedEvent;
import ru.stepanovgzh.wct.orderingms.data.model.PickingOrder;
import ru.stepanovgzh.wct.orderingms.data.view.PickingOrderView;

@Mapper(componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PickingOrderMapper 
{
    PickingOrder map(PickingOrderCreatedEvent pickingOrderCreatedEvent);
    PickingOrderView map(PickingOrder receivingOrder);
}
