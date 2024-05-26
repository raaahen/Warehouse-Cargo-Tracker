package ru.stepanovgzh.wct.receivingms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ru.stepanovgzh.wct.receivingms.cqrs.event.ReceivingOrderCreatedEvent;
import ru.stepanovgzh.wct.receivingms.data.model.ReceivingOrder;
import ru.stepanovgzh.wct.receivingms.data.view.ReceivingOrderView;

@Mapper(componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReceivingOrderMapper
{
    ReceivingOrder map(ReceivingOrderCreatedEvent receivingOrderCreatedEvent);
    ReceivingOrderView map(ReceivingOrder receivingOrder);
}
