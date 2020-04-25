
package com.sabara.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PowerstatsDTO {

    private int combat;
    private int durability;
    private int intelligence;
    private int power;
    private int speed;
    private int strength;
}
