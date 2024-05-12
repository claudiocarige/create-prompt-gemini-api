package com.claudiocarige.googleiagemini.core.domain.entities;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenerationConfig {

    private double temperature;
    private int maxOutputTokens;
    private double topP;
    private int topK;
}
