package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.PackType;

@Value
public class AddDetailToReceivingOrderInput 
{
    @NotNull
    UUID receivingOrderId;

    @NotBlank
    @Size(max = 50)
    String skuBarcode;

    @NotBlank
    @Size(max = 100)
    String skuName;

    @NotBlank
    @Size(max = 255)
    String skuDecription;

    @Min(value = 1)
    int qty;

    @NotNull
    PackType packType;

    @NotBlank
    @Size(max = 255)
    String packDescription;
}
