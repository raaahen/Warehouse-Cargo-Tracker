package ru.stepanovgzh.wct.orderingms.data.input;

import java.util.Date;
import java.util.UUID;

import lombok.Value;

@Value
public class CreatePickingOrderInput 
{
    private UUID clientId;
    private String clientName;
    private UUID transporterId;
    private String transporterName;
    private Date preparationDeadline;
}
