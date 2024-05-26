package ru.stepanovgzh.wct.receivingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.SkuReceivingStatus;

@Value
public class ReceiveCargoCommand 
{
    @TargetAggregateIdentifier
    UUID receivingOrderId;
    UUID detailId;
    UUID cargoId;
    SkuReceivingStatus skuReceivingStatus;
}
