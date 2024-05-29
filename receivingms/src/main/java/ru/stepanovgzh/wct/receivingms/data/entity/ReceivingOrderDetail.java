package ru.stepanovgzh.wct.receivingms.data.entity;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.wct.receivingms.data.value.Pack;
import ru.stepanovgzh.wct.receivingms.data.value.Sku;
import ru.stepanovgzh.wct.receivingms.data.value.SkuReceivingStatus;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ReceivingOrderDetail 
{
    @Id
    UUID id;

    UUID receivingOrderId;

    @Embedded
    Sku sku;

    int qty;

    @Embedded
    Pack pack;

    UUID receivedCargoId;

    SkuReceivingStatus skuReceivingStatus;

    public ReceivingOrderDetail(UUID id, UUID receivingOrderId, Sku sku, int qty, Pack pack)
    {
        this.id = id;
        this.receivingOrderId = receivingOrderId;
        this.sku = sku;
        this.qty = qty;
        this.pack = pack;
    }
}
