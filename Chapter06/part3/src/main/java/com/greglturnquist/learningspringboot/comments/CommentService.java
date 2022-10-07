package com.greglturnquist.learningspringboot.comments;

import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author Greg Turnquist
 */
// tag::stream-1[]
@Service
@EnableBinding(CustomProcessor.class)
public class CommentService {
	// end::stream-1[]

	private final CommentWriterRepository repository;

	private final MeterRegistry meterRegistry;

	public CommentService(CommentWriterRepository repository,
						  MeterRegistry meterRegistry) {
		this.repository = repository;
		this.meterRegistry = meterRegistry;
	}

	// tag::stream-2[]
	@StreamListener
	@Output(CustomProcessor.OUTPUT)
	public Flux<Void> save(@Input(CustomProcessor.INPUT)
						   Flux<Comment> newComments) {
		return repository
			.saveAll(newComments)
			.flatMap(comment -> {
				meterRegistry
					.counter("comments.consumed", "imageId", comment.getImageId())
					.increment();
				return Mono.empty();
			});
	}
	// end::stream-2[]

	@Bean
	CommandLineRunner setUp(CommentWriterRepository repository) {
		return args -> {
			repository.deleteAll().subscribe();
		};
	}

}
