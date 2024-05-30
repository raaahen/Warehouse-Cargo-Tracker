package ru.stepanovgzh.wct.pickingms.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import ru.stepanovgzh.wct.pickingms.data.entity.Client;
import ru.stepanovgzh.wct.pickingms.data.entity.PickingOrderDetail;
import ru.stepanovgzh.wct.pickingms.data.entity.Transporter;
import ru.stepanovgzh.wct.pickingms.data.value.PickingStatus;

@Data
@Entity
public class PickingOrder 
{
    @Id
    UUID id;

    @ManyToOne
    Client client;

    @ManyToOne
    Transporter transporter;

    @Temporal(TemporalType.TIMESTAMP)
    Date preparationDeadline;

    PickingStatus status;

    @OneToMany(mappedBy = "pickingOrderId",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    List<PickingOrderDetail> details = new ArrayList<>();

    public void removeDetail(PickingOrderDetail detail)
    {
        details.remove(detail);
        detail.setPickingOrderId(null);
    }
}
