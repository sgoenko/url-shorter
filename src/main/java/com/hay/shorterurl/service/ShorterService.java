package com.hay.shorterurl.service;

import com.hay.shorterurl.controller.ShorterController;
import com.hay.shorterurl.entity.Shorter;
import com.hay.shorterurl.repository.ShorterRepository;
import com.hay.shorterurl.util.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ShorterService {
	Logger logger = LoggerFactory.getLogger(ShorterController.class.getSimpleName());

	private final ShorterRepository repository;
	private final CodeGenerator codeGenerator;

	@Value("${shorter.length}")
	private Integer shorterLength;

	@Autowired
	public ShorterService(final ShorterRepository repository, CodeGenerator codeGenerator) {
		this.repository = repository;
		this.codeGenerator = new CodeGenerator();
	}

	public Shorter getByHash(String hash) {
		return repository.findByHash(hash);
	}

	public Shorter addHash(Shorter shorter) {
		String hash = codeGenerator.generate(shorterLength);
		logger.info(hash);

		String shorterString = URLDecoder.decode(shorter.getOriginalUrl());
		logger.info(shorterString);
		shorter = new Shorter(null, hash, shorterString, ZonedDateTime.now());
		return repository.save(shorter);
	}

	public List<Shorter> getAll() {
		return (List<Shorter>) repository.findAll();
	}
}
