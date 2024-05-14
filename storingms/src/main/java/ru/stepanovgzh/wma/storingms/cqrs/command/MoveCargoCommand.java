package ru.stepanovgzh.wma.storingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class MoveCargoCommand 
{
    @TargetAggregateIdentifier
    UUID id;
    String zone;
    String cell;
}
