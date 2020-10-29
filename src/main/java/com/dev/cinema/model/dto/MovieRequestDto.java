package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class MovieRequestDto {
    @NotNull(message = "Title can't be null")
    private String title;
    @NotNull(message = "Description can't be null")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
