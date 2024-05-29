package ru.stepanovgzh.wct.receivingms.data.view;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.entity.ReceivingOrderDetail;
import ru.stepanovgzh.wct.receivingms.data.entity.Supplier;
import ru.stepanovgzh.wct.receivingms.data.entity.Transporter;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;

@Value
public class ReceivingOrderView 
{
    UUID id;
    Supplier supplier;
    Transporter transporter;
    Date receivingDate;
    ReceivingStatus receivingStatus;
    List<ReceivingOrderDetail> details;
}
