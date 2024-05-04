package ru.stepanovgzh.wms.skumanagement.aggregates;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import lombok.NoArgsConstructor;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.command.AddSkuCommand;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.command.RemoveSkuCommand;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.command.UpdateSkuInfoCommand;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuAddedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuInfoUpdatedEvent;
import ru.stepanovgzh.wms.skumanagement.cqrs.sku.event.SkuRemovedEvent;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Barcode;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Description;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Name;

@Aggregate
@NoArgsConstructor
public class SkuAggregate
{
    @AggregateIdentifier
    private UUID skuId;
    private Barcode barcode;
    private Name name;
    private Description description;

    @CommandHandler
    public SkuAggregate(AddSkuCommand addSkuCommand)
    {
        AggregateLifecycle.apply(new SkuAddedEvent(
            addSkuCommand.getSkuId(),
            new Barcode(addSkuCommand.getBarcode()),
            new Name(addSkuCommand.getName()),
            new Description(addSkuCommand.getDescription())));
    }

    @EventSourcingHandler
    public void on(SkuAddedEvent skuAddedEvent)
    {
        this.skuId = skuAddedEvent.getSkuId();
        this.barcode = skuAddedEvent.getBarcode();
        this.name = skuAddedEvent.getName();
        this.description = skuAddedEvent.getDescription();
    }

    @CommandHandler
    public void handle(UpdateSkuInfoCommand updateSkuInfoCommand)
    {
        AggregateLifecycle.apply(new SkuInfoUpdatedEvent(
            updateSkuInfoCommand.getSkuId(),
            new Barcode(updateSkuInfoCommand.getBarcode()),
            new Name(updateSkuInfoCommand.getName()),
            new Description(updateSkuInfoCommand.getDescription())));
    }

    @EventSourcingHandler
    public void on(SkuInfoUpdatedEvent skuInfoUpdatedEvent)
    {
        this.name = skuInfoUpdatedEvent.getName();
        this.description = skuInfoUpdatedEvent.getDescription();
    }

    @CommandHandler
    public void handle(RemoveSkuCommand removeSkuCommand)
    {
        AggregateLifecycle.apply(new SkuRemovedEvent(
            removeSkuCommand.getSkuId()));
    }

    @EventSourcingHandler
    public void on(SkuRemovedEvent skuRemovedEvent)
    {
        AggregateLifecycle.markDeleted();
    }
}
