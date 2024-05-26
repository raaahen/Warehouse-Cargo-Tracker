package ru.stepanovgzh.wct.storingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class UpdateCargoCommand 
{
    @TargetAggregateIdentifier
    UUID id;
    int qty;
}
