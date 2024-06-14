package br.com.rinicius.urlshortener.controller.dto;

import jakarta.validation.constraints.NotNull;

public record CreateUrlShort(@NotNull String url) {

}
