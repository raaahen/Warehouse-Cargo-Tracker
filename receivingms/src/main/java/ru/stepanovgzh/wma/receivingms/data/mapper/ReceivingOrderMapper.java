package ru.stepanovgzh.wma.receivingms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ru.stepanovgzh.wma.receivingms.cqrs.event.ReceivingOrderCreatedEvent;
import ru.stepanovgzh.wma.receivingms.data.model.ReceivingOrder;
import ru.stepanovgzh.wma.receivingms.data.view.ReceivingOrderView;

@Mapper(componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReceivingOrderMapper
{
    ReceivingOrder map(ReceivingOrderCreatedEvent receivingOrderCreatedEvent);
    ReceivingOrderView map(ReceivingOrder receivingOrder);
}
