package de.fhdo.wegistweg.dto;

import jakarta.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
