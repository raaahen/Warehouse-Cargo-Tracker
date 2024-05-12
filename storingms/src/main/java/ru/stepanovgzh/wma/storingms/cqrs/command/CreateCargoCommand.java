package ru.stepanovgzh.wma.storingms.cqrs.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;
import ru.stepanovgzh.wma.storingms.data.value.PackType;

@Value
public class CreateCargoCommand 
{
    @TargetAggregateIdentifier
    private UUID id;
    String skuBarcode;
    String skuName;
    String skuDescription;
    PackType packType;
    String packDescription;
    int qtyOfSku;
}
