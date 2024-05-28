package ru.stepanovgzh.wct.storingms.data.model;

import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.wct.storingms.data.value.CargoStatus;
import ru.stepanovgzh.wct.storingms.data.value.Location;
import ru.stepanovgzh.wct.storingms.data.value.Pack;
import ru.stepanovgzh.wct.storingms.data.value.Sku;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cargo 
{
    @Id
    private UUID id;

    @Embedded
    private Sku sku;

    @Embedded
    private Pack pack;

    private int qty;

    @Embedded
    private Location location;
    
    private CargoStatus status;
}
