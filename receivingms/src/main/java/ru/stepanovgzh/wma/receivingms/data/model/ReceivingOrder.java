package ru.stepanovgzh.wma.receivingms.data.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import ru.stepanovgzh.wma.receivingms.data.entity.ReceivingOrderDetail;
import ru.stepanovgzh.wma.receivingms.data.entity.Supplier;
import ru.stepanovgzh.wma.receivingms.data.entity.Transporter;
import ru.stepanovgzh.wma.receivingms.data.value.ReceivingStatus;

@Data
@Entity
public class ReceivingOrder
{
    @Id
    UUID id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "transporter_id")
    Transporter transporter;

    @Temporal(TemporalType.TIMESTAMP)
    Date receivingDate;

    ReceivingStatus receivingStatus;

    @OneToMany(mappedBy = "receivingOrderId", fetch = FetchType.EAGER)
    List<ReceivingOrderDetail> details;
}
