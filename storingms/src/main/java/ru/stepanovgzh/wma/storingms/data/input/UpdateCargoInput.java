package ru.stepanovgzh.wma.storingms.data.input;

import java.util.UUID;

import lombok.Value;

@Value
public class UpdateCargoInput 
{
    UUID id;
    int qty;
}
