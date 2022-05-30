package com.hay.shorterurl.repository;

import com.hay.shorterurl.entity.Shorter;
import org.springframework.data.repository.CrudRepository;

public interface ShorterRepository extends CrudRepository<Shorter, Long> {
	Shorter findByHash(String hash);
}
