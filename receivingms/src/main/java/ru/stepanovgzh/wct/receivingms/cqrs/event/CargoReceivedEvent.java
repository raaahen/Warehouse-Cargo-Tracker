package ru.stepanovgzh.wct.receivingms.cqrs.event;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.SkuReceivingStatus;

@Value
public class CargoReceivedEvent 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
    UUID receivedCargoId;
    SkuReceivingStatus skuReceivingStatus;
}
