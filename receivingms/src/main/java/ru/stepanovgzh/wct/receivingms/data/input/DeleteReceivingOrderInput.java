package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteReceivingOrderInput 
{
    @NotNull
    UUID id;
}
