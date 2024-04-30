package ru.stepanovgzh.wms.productmanagement.data.model;

import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.wms.productmanagement.data.model.types.Barcode;
import ru.stepanovgzh.wms.productmanagement.data.model.types.Description;
import ru.stepanovgzh.wms.productmanagement.data.model.types.Name;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product 
{
    @AggregateIdentifier
    private UUID productId;
    private Barcode barcode;
    private Name name;
    private Description description;
}
