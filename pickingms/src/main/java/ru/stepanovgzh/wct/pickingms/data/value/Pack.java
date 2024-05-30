package ru.stepanovgzh.wct.pickingms.data.value;

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
public class Pack 
{
    @Column(name = "pack_type")
    PackType type;
    @Column(name = "pack_description")
    String description;
}
