package ru.stepanovgzh.wms.skumanagement.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.AllSkuGroupsQuery;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupAddedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupInfoUpdatedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupRemovedEvent;
import ru.stepanovgzh.wms.skumanagement.data.model.EntityMapper;
import ru.stepanovgzh.wms.skumanagement.data.model.SkuGroup;
import ru.stepanovgzh.wms.skumanagement.data.repository.SkuGroupRepository;
import ru.stepanovgzh.wms.skumanagement.data.view.SkuGroupView;

@Service
@RequiredArgsConstructor
public class SkuGroupProjection 
{
    private final EntityMapper mapper;
    private final SkuGroupRepository repository;

    @EventHandler
    public void on(SkuGroupAddedEvent skuGroupAddedEvent)
    {
        repository.save(mapper.map(skuGroupAddedEvent));
    }

    @EventHandler
    public void on(SkuGroupInfoUpdatedEvent skuGroupInfoUpdatedEvent)
    {
        SkuGroup skuGroupFromDb = repository.findById(skuGroupInfoUpdatedEvent.getSkuGroupId())
            .orElseThrow(() -> new EntityNotFoundException(
                "SkuGroup with id %s not found " + skuGroupInfoUpdatedEvent.getSkuGroupId()));

        SkuGroup skuGroupFromEvent = mapper.map(skuGroupInfoUpdatedEvent);
        SkuGroup skuGroupProjection = mapper.merge(skuGroupFromEvent, skuGroupFromDb);
        repository.save(skuGroupProjection);
    }

    @EventHandler
    public void on(SkuGroupRemovedEvent skuGroupRemovedEvent)
    {
        repository.deleteById(skuGroupRemovedEvent.getSkuGroupId());
    }

    @QueryHandler
    public List<SkuGroupView> handle(AllSkuGroupsQuery allSkuGroupsQuery)
    {
        return repository.findAll().stream()
            .map(mapper::map)
            .collect(Collectors.toList());
    }
}
