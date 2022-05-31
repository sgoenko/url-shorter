package com.hay.shorterurl.service;

import com.hay.shorterurl.entity.Shorter;
import com.hay.shorterurl.repository.ShorterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShorterService {

	private final ShorterRepository repository;

	@Value("${shorter.length}")
	private Integer shorterLength;

	public Shorter getByHash(String hash) {
		return repository.findByHash(hash);
	}

	public Shorter addHash(Shorter shorter) {
		String hash = RandomStringUtils.randomAlphanumeric(shorterLength);
		log.info(hash);

		String shorterString = URLDecoder.decode(shorter.getOriginalUrl());
		log.info(shorterString);
		shorter = new Shorter(null, hash, shorterString, ZonedDateTime.now());
		return repository.save(shorter);
	}

	public List<Shorter> getAll() {
		return (List<Shorter>) repository.findAll();
	}
}
