package ru.stepanovgzh.wms.productmanagement.domain.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ru.stepanovgzh.wms.productmanagement.domain.events.product.ProductAddedEvent;
import ru.stepanovgzh.wms.productmanagement.domain.events.product.ProductInfoUpdatedEvent;
import ru.stepanovgzh.wms.productmanagement.domain.view.ProductView;

@Mapper(componentModel = "spring")
public interface EntityMapper 
{
    Product map(ProductAddedEvent productAddedEvent);
    Product map(ProductInfoUpdatedEvent productInfoUpdatedEvent);
    Product merge(Product productFromEvent, @MappingTarget Product productFromDb);
    ProductView map(Product product);
}
