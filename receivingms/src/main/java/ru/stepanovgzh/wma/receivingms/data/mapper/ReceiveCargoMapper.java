package ru.stepanovgzh.wma.receivingms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ru.stepanovgzh.wma.receivingms.cqrs.event.ReceivingOrderCreatedEvent;
import ru.stepanovgzh.wma.receivingms.data.model.ReceivingOrder;

@Mapper(componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReceiveCargoMapper
{
    ReceivingOrder map(ReceivingOrderCreatedEvent receivingOrderCreatedEvent);
}
