package com.hay.shorterurl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Shorter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String hash;

	@Column
	private String originalUrl;

	@Column(columnDefinition = "TIMESTAMP")
	private ZonedDateTime createdAt;
}
