package ru.stepanovgzh.wms.skumanagement.aggregates;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.command.AddSkuGroupCommand;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.command.RemoveSkuGroupCommand;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.command.UpdateSkuGroupInfoCommand;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupAddedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupInfoUpdatedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.skugroup.event.SkuGroupRemovedEvent;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Name;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Description;

@Aggregate
@NoArgsConstructor
public class SkuGroupAggregate 
{
    @AggregateIdentifier
    UUID skuGroupId;
    Name name;
    Description description;

    @CommandHandler
    public SkuGroupAggregate(AddSkuGroupCommand addSkuGroupCommand)
    {
        AggregateLifecycle.apply(new SkuGroupAddedEvent(
            addSkuGroupCommand.getSkuGroupId(),
            new Name(addSkuGroupCommand.getName()),
            new Description(addSkuGroupCommand.getDescription())));
    }

    @EventSourcingHandler
    public void on(SkuGroupAddedEvent skuGroupAddedEvent)
    {
        this.skuGroupId = skuGroupAddedEvent.getSkuGroupId();
        this.name = skuGroupAddedEvent.getName();
        this.description = skuGroupAddedEvent.getDescription();
    }

    @CommandHandler
    public void handle(UpdateSkuGroupInfoCommand updateSkuGroupInfoCommand)
    {
        AggregateLifecycle.apply(new SkuGroupInfoUpdatedEvent(
            updateSkuGroupInfoCommand.getSkuGroupId(),
            new Name(updateSkuGroupInfoCommand.getName()),
            new Description(updateSkuGroupInfoCommand.getDescription())));
    }

    @EventSourcingHandler
    public void on(SkuGroupInfoUpdatedEvent skuGroupInfoUpdatedEvent)
    {
        this.name = skuGroupInfoUpdatedEvent.getName();
        this.description = skuGroupInfoUpdatedEvent.getDescription();
    }

    @CommandHandler
    public void handle(RemoveSkuGroupCommand removeSkuGroupCommand)
    {
        AggregateLifecycle.apply(new SkuGroupRemovedEvent(
            removeSkuGroupCommand.getSkuGroupId()));
    }

    @EventSourcingHandler
    public void on(SkuGroupRemovedEvent skuGroupRemovedEvent)
    {
        AggregateLifecycle.markDeleted();
    }
}
