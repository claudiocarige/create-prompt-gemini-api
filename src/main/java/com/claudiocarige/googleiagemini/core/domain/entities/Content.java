package com.claudiocarige.googleiagemini.core.domain.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Content {

    @Setter(AccessLevel.NONE)
    public List<Part> parts = new ArrayList<>();
    public String role;
    public void addPart(Part part) {
        parts.add(part);
    }

}
