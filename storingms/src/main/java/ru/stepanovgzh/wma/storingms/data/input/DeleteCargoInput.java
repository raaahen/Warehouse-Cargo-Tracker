package ru.stepanovgzh.wma.storingms.data.input;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class DeleteCargoInput 
{
    @TargetAggregateIdentifier
    UUID id;
}
