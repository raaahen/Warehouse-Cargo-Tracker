package ru.stepanovgzh.wms.productmanagement.domain.aggregates;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import lombok.NoArgsConstructor;
import ru.stepanovgzh.wms.productmanagement.domain.commands.product.AddProductCommand;
import ru.stepanovgzh.wms.productmanagement.domain.commands.product.RemoveProductCommand;
import ru.stepanovgzh.wms.productmanagement.domain.commands.product.UpdateProductInfoCommand;
import ru.stepanovgzh.wms.productmanagement.domain.events.product.ProductAddedEvent;
import ru.stepanovgzh.wms.productmanagement.domain.events.product.ProductInfoUpdatedEvent;
import ru.stepanovgzh.wms.productmanagement.domain.events.product.ProductRemovedEvent;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Barcode;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Description;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Name;

@Aggregate
@NoArgsConstructor
public class ProductAggregate
{
    @AggregateIdentifier
    private UUID productId;
    private Barcode barcode;
    private Name name;
    private Description description;

    @CommandHandler
    public ProductAggregate(AddProductCommand addProductCommand)
    {
        AggregateLifecycle.apply(new ProductAddedEvent(
            addProductCommand.getProductId(),
            new Barcode(addProductCommand.getBarcode()),
            new Name(addProductCommand.getName()),
            new Description(addProductCommand.getDescription())));
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent productAddedEvent)
    {
        this.productId = productAddedEvent.getProductId();
        this.barcode = productAddedEvent.getBarcode();
        this.name = productAddedEvent.getName();
        this.description = productAddedEvent.getDescription();
    }

    @CommandHandler
    public void handle(UpdateProductInfoCommand updateProductInfoCommand)
    {
        AggregateLifecycle.apply(new ProductInfoUpdatedEvent(
            updateProductInfoCommand.getProductId(),
            new Barcode(updateProductInfoCommand.getBarcode()),
            new Name(updateProductInfoCommand.getName()),
            new Description(updateProductInfoCommand.getDescription())));
    }

    @EventSourcingHandler
    public void on(ProductInfoUpdatedEvent productInfoUpdatedEvent)
    {
        this.name = productInfoUpdatedEvent.getName();
        this.description = productInfoUpdatedEvent.getDescription();
    }

    @CommandHandler
    public void handle(RemoveProductCommand removeProductCommand)
    {
        AggregateLifecycle.apply(new ProductRemovedEvent(
            removeProductCommand.getProductId()));
    }

    public void on(ProductRemovedEvent productRemovedEvent)
    {
        AggregateLifecycle.markDeleted();
    }
}
