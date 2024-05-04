package ru.stepanovgzh.wms.skumanagement.data.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuAddedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuInfoUpdatedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupAddedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupInfoUpdatedEvent;
import ru.stepanovgzh.wms.skumanagement.data.view.SkuGroupView;
import ru.stepanovgzh.wms.skumanagement.data.view.SkuView;

@Mapper(componentModel = "spring")
public interface EntityMapper
{
    Sku map(SkuAddedEvent skuAddedEvent);
    Sku map(SkuInfoUpdatedEvent skuInfoUpdatedEvent);
    Sku merge(Sku skuFromEvent, @MappingTarget Sku skuFromDb);
    SkuView map(Sku sku);

    SkuGroup map(SkuGroupAddedEvent skuGroupAddedEvent);
    SkuGroup map(SkuGroupInfoUpdatedEvent skuGroupInfoUpdatedEvent);
    SkuGroup merge(SkuGroup skuGroupFromEvent, @MappingTarget SkuGroup skuGroupFromDb);
    SkuGroupView map(SkuGroup skuGroup);
}
