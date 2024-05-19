package ru.stepanovgzh.wma.receivingms.data.entity;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.stepanovgzh.wma.receivingms.data.value.Pack;
import ru.stepanovgzh.wma.receivingms.data.value.Sku;
import ru.stepanovgzh.wma.receivingms.data.value.SkuReceivingStatus;

@Data
@Entity
public class ReceivingOrderDetail 
{
    @Id
    UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiving_order_id")
    UUID receivingOrderId;

    @Embedded
    Sku sku;

    int qty;

    @Embedded
    Pack pack;

    UUID receivedCargoId;

    SkuReceivingStatus skuReceivingStatus;
}
