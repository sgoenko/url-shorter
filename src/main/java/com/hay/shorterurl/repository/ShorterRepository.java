package com.hay.shorterurl.repository;

import com.hay.shorterurl.entity.Shorter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShorterRepository extends CrudRepository<Shorter, Long> {

	Shorter findByHash(String hash);

	Optional<Shorter> findByOriginalUrl(String originalUrl);
}
