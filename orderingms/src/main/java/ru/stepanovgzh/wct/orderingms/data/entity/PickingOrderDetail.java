package ru.stepanovgzh.wct.orderingms.data.entity;

import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.wct.orderingms.data.value.Pack;
import ru.stepanovgzh.wct.orderingms.data.value.Sku;
import ru.stepanovgzh.wct.orderingms.data.value.SkuPickingStatus;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PickingOrderDetail
{
    @Id
    UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "picking_order_id")
    UUID pickingOrderId;

    @Embedded
    Sku sku;

    int qty;

    @Embedded
    Pack pack;

    UUID pickedCargoId;

    SkuPickingStatus skuPickingStatus;

    public PickingOrderDetail(UUID id, UUID pickingOrderId, Sku sku, int qty, Pack pack)
    {
        this.id = id;
        this.pickingOrderId = pickingOrderId;
        this.sku = sku;
        this.qty = qty;
        this.pack = pack;
    }
}

