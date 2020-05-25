package com.sabara.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "hero_stats")
@Accessors(chain = true)
public class HeroStats {

    @Id
    private String id;

    private String hero;
    private Long pickCount;
    private Long winCount;
    private Long loseCount;
}
