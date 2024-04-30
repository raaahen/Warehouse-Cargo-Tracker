package ru.stepanovgzh.wms.productmanagement.data.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ru.stepanovgzh.wms.productmanagement.cqrs.product.event.ProductAddedEvent;
import ru.stepanovgzh.wms.productmanagement.cqrs.product.event.ProductInfoUpdatedEvent;
import ru.stepanovgzh.wms.productmanagement.data.view.ProductView;

@Mapper(componentModel = "spring")
public interface EntityMapper 
{
    Product map(ProductAddedEvent productAddedEvent);
    Product map(ProductInfoUpdatedEvent productInfoUpdatedEvent);
    Product merge(Product productFromEvent, @MappingTarget Product productFromDb);
    ProductView map(Product product);
}
