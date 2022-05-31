package com.hay.shorterurl.controller;

import com.hay.shorterurl.entity.Shorter;
import com.hay.shorterurl.service.ShorterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShorterController {
	private final ShorterService shorterService;

	@PostMapping(path = "/")
	public ResponseEntity createShortUrl(@RequestBody Shorter shorter) {
		shorter = shorterService.addHash(shorter);
		return ResponseEntity.ok(shorter);
	}

	@GetMapping(path = "/{hash}")
	public ResponseEntity redirectShorter(@PathVariable("hash") String hash) {
		Shorter shorter = shorterService.getByHash(hash);
		if (shorter != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", shorter.getOriginalUrl());
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.ok(shorterService.getAll());
	}

}
