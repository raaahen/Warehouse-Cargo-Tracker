package ru.stepanovgzh.wma.receivingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wma.receivingms.data.value.SkuReceivingStatus;

@Value
public class CargoReceivedEvent 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
    UUID receivedCargoId;
    SkuReceivingStatus skuReceivingStatus;
}
