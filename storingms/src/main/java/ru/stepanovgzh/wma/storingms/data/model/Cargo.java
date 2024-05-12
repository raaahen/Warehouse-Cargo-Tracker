package ru.stepanovgzh.wma.storingms.data.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.wma.storingms.data.value.CargoStatus;
import ru.stepanovgzh.wma.storingms.data.value.Location;
import ru.stepanovgzh.wma.storingms.data.value.Pack;
import ru.stepanovgzh.wma.storingms.data.value.Sku;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cargo 
{
    @Id
    private UUID id;
    Sku sku;
    Pack pack;
    int qty;
    Location location;
    CargoStatus status;
}
