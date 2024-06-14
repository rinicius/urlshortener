package br.com.rinicius.urlshortener.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rinicius.urlshortener.entity.UrlShortEntity;

public interface UrlShortRepository extends JpaRepository<UrlShortEntity, UUID> { // O tipo em <> (no caso UrlShortEntity) referencia a tabela que será usada pelo 'orm'

    boolean existsByShortUrl(String shortUrl);

    Optional<UrlShortEntity> findByShortUrl(String shortUrl);
}
