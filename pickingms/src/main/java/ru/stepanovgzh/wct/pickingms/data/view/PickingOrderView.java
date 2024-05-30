package ru.stepanovgzh.wct.pickingms.data.view;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.pickingms.data.entity.*;
import ru.stepanovgzh.wct.pickingms.data.value.PickingStatus;

@Value
public class PickingOrderView 
{
    UUID id;
    Client client;
    Transporter transporter;
    Date preparationDeadline;
    PickingStatus pickingStatus;
    List<PickingOrderDetailView> details;
}
