package ru.stepanovgzh.wma.receivingms.data.input;

import java.util.Date;
import java.util.UUID;

public class CreateReceivingOrderInput 
{
    UUID id;
    UUID supplierId;
    String supplierName;
    UUID transporterId;
    String transporterName;
    Date date;
}
