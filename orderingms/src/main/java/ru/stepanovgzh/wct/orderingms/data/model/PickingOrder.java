package ru.stepanovgzh.wct.orderingms.data.model;

import java.util.ArrayList;
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
import ru.stepanovgzh.wct.orderingms.data.entity.Client;
import ru.stepanovgzh.wct.orderingms.data.entity.PickingOrderDetail;
import ru.stepanovgzh.wct.orderingms.data.entity.Transporter;
import ru.stepanovgzh.wct.orderingms.data.value.PickingStatus;

@Data
@Entity
public class PickingOrder 
{
    @Id
    UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name = "transporter_id")
    Transporter transporter;

    @Temporal(TemporalType.TIMESTAMP)
    Date preparationDeadline;

    PickingStatus status;

    @OneToMany(mappedBy = "pickingOrderId", fetch = FetchType.EAGER)
    List<PickingOrderDetail> details = new ArrayList<>();
}
