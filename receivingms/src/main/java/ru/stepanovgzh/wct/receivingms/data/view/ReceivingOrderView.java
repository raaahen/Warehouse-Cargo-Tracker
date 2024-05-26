package ru.stepanovgzh.wct.receivingms.data.view;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;

@Value
public class ReceivingOrderView 
{
    UUID id;
    UUID supplierId;
    String supplierName;
    UUID transporterId;
    String transporterName;
    Date receivingDate;
    ReceivingStatus receivingStatus;
    List<ReceivingOrderDetailView> details;
}
