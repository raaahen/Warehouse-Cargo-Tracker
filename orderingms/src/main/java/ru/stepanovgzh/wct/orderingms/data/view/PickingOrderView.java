package ru.stepanovgzh.wct.orderingms.data.view;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.value.PickingStatus;

@Value
public class PickingOrderView 
{
    UUID id;
    UUID clientId;
    String clientName;
    UUID transporterId;
    String transporterName;
    Date pickingDate;
    PickingStatus pickingStatus;
    List<PickingOrderDetailView> details;
}
