package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.Date;
import java.util.UUID;

import lombok.Value;

@Value
public class CreateReceivingOrderInput 
{
    UUID supplierId;
    String supplierName;
    UUID transporterId;
    String transporterName;
    Date date;
}
