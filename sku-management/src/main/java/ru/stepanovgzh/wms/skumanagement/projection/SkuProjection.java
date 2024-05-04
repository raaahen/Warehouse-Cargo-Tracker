package ru.stepanovgzh.wms.skumanagement.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.AllSkusQuery;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuAddedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuInfoUpdatedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuRemovedEvent;
import ru.stepanovgzh.wms.skumanagement.data.model.EntityMapper;
import ru.stepanovgzh.wms.skumanagement.data.model.Sku;
import ru.stepanovgzh.wms.skumanagement.data.repository.SkuRepository;
import ru.stepanovgzh.wms.skumanagement.data.view.SkuView;

@Service
@RequiredArgsConstructor
public class SkuProjection 
{
    private final EntityMapper mapper;
    private final SkuRepository repository;

    @EventHandler
    public void on(SkuAddedEvent skuAddedEvent)
    {
        repository.save(mapper.map(skuAddedEvent));
    }

    @EventHandler
    public void on(SkuInfoUpdatedEvent skuInfoUpdatedEvent)
    {
        Sku skuFromDb = repository.findById(skuInfoUpdatedEvent.getSkuId())
            .orElseThrow(() -> new EntityNotFoundException(
                "Sku with id %s not found " + skuInfoUpdatedEvent.getSkuId()));

        Sku skuFromEvent = mapper.map(skuInfoUpdatedEvent);
        Sku skuProjection = mapper.merge(skuFromEvent, skuFromDb);
        repository.save(skuProjection);
    }

    @EventHandler
    public void on(SkuRemovedEvent skuRemovedEvent)
    {
        repository.deleteById(skuRemovedEvent.getSkuId());
    }

    @QueryHandler
    public List<SkuView> handle(AllSkusQuery allSkusQuery)
    {
        return repository.findAll().stream()
            .map(mapper::map)
            .collect(Collectors.toList());
    }
}
