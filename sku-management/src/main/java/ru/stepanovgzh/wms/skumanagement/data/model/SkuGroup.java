package ru.stepanovgzh.wms.skumanagement.data.model;

import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.wms.skumanagement.data.model.types.Description;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SkuGroup 
{
    @AggregateIdentifier
    UUID skuGroupId;
    Name name;
    Description description;
}
