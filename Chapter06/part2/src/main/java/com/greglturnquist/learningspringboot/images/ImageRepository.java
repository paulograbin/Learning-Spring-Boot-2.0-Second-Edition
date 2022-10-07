package com.greglturnquist.learningspringboot.images;

import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author Greg Turnquist
 */
public interface ImageRepository
	extends ReactiveCrudRepository<Image, String> {

	Mono<Image> findByName(String name);
}
