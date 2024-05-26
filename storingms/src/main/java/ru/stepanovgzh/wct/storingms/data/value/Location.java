package ru.stepanovgzh.wct.storingms.data.value;

import jakarta.persistence.Embeddable;
import lombok.Value;

@Value
@Embeddable
public class Location 
{
    String zone;
    String cell;
}