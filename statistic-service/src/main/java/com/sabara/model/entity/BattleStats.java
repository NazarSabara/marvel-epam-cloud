package com.sabara.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "battle_stats")
@Accessors(chain = true)
public class BattleStats {

    @Id
    private String id;

    private long battleDuration;
}
