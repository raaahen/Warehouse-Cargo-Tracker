package ru.stepanovgzh.wct.receivingms.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.CascadeType;
import lombok.Data;
import ru.stepanovgzh.wct.receivingms.data.entity.ReceivingOrderDetail;
import ru.stepanovgzh.wct.receivingms.data.entity.Supplier;
import ru.stepanovgzh.wct.receivingms.data.entity.Transporter;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;

@Data
@Entity
public class ReceivingOrder
{
    @Id
    UUID id;

    @ManyToOne
    Supplier supplier;

    @ManyToOne
    Transporter transporter;

    @Temporal(TemporalType.TIMESTAMP)
    Date receivingDate;

    ReceivingStatus receivingStatus;

    @OneToMany(mappedBy = "receivingOrderId",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    List<ReceivingOrderDetail> details = new ArrayList<>();

    public void removeDetail(ReceivingOrderDetail detail)
    {
        details.remove(detail);
        detail.setReceivingOrderId(null);
    }
}
