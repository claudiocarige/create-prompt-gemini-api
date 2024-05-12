package com.claudiocarige.googleiagemini.core.domain.entities;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Root {
    @Setter(AccessLevel.NONE)
    public ArrayList<Content> contents = new ArrayList<>();
    public GenerationConfig generationConfig;

    public void addContent(Content content) {
        contents.add(content);
    }
}

