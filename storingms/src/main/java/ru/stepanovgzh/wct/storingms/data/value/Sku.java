package ru.stepanovgzh.wct.storingms.data.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Sku 
{
    @Column(name = "sku_barcode")
    String barcode;
    @Column(name = "sku_name")
    String name;
    @Column(name = "sku_description")
    String description;
}
