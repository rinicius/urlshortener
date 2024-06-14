package br.com.rinicius.urlshortener.service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import br.com.rinicius.urlshortener.controller.dto.CreateUrlShort;
import br.com.rinicius.urlshortener.entity.UrlShortEntity;
import br.com.rinicius.urlshortener.repository.UrlShortRepository;

@Service
public class UrlShortService {
    private final UrlShortRepository urlShortRepository;

    public UrlShortService(UrlShortRepository urlShortRepository) {
        this.urlShortRepository = urlShortRepository;
    }

    public Optional<UrlShortEntity> findByShortUrl(String shortUrl) {
        return this.urlShortRepository.findByShortUrl(shortUrl);
    }

    public UrlShortEntity create(CreateUrlShort create) {
        UrlShortEntity entity = new UrlShortEntity();

        entity.setUrl(create.url());

        String shortUrl;
        do {
            shortUrl = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (this.urlShortRepository.existsByShortUrl(shortUrl));

        entity.setShortUrl(shortUrl);

        return this.urlShortRepository.save(entity);
    }
}
