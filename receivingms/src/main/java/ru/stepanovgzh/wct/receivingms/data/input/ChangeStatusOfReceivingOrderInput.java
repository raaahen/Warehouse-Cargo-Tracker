package ru.stepanovgzh.wct.receivingms.data.input;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.ReceivingStatus;

import java.util.UUID;

@Value
public class ChangeStatusOfReceivingOrderInput
{
    @NotNull
    UUID id;

    @NotNull
    ReceivingStatus status;
}
