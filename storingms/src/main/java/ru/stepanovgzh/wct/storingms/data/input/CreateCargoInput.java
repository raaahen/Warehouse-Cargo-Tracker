package ru.stepanovgzh.wct.storingms.data.input;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.PackType;

@Value
public class CreateCargoInput 
{
    @NotNull
    UUID id;

    @NotBlank
    @Size(min = 1, max = 100)
    String skuBarcode;

    @NotBlank
    @Size(min = 1, max = 100)
    String skuName;

    @Size(max = 500)
    String skuDescription;

    @NotNull
    PackType packType;

    @Size(max = 500)
    String packDescription;

    @Min(1)
    int qtyOfSku;
}
