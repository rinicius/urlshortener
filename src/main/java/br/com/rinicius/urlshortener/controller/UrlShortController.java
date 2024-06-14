package br.com.rinicius.urlshortener.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.rinicius.urlshortener.controller.dto.CreateUrlShort;
import br.com.rinicius.urlshortener.controller.dto.ShortenUrlResponse;
import br.com.rinicius.urlshortener.service.UrlShortService;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UrlShortController {
    private final UrlShortService urlShortService;

    public UrlShortController(UrlShortService urlShortService) {
        this.urlShortService = urlShortService;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<ShortenUrlResponse> create(@RequestBody CreateUrlShort createUrlShort,
            HttpServletRequest servletRequest) {
        var shortUrl = this.urlShortService.create(createUrlShort);

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", shortUrl.getShortUrl());

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable("shortUrl") String shortUrl) {

        var url = urlShortService.findByShortUrl(shortUrl);

        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
