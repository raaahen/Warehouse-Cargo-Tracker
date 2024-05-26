package ru.stepanovgzh.wct.storingms.data.input;

import java.util.UUID;

import lombok.Value;

@Value
public class MoveCargoInput 
{
    UUID id;
    String zone;
    String cell;
}
