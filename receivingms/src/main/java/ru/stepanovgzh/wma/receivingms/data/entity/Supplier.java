package ru.stepanovgzh.wma.receivingms.data.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Supplier 
{
    @Id
    UUID id;
    String name;
}
