package ru.stepanovgzh.wms.productmanagement.domain.model;

import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Barcode;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Description;
import ru.stepanovgzh.wms.productmanagement.domain.model.types.Name;

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
