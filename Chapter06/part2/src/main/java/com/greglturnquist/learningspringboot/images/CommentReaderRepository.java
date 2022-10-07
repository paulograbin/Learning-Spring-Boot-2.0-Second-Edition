// tag::code[]
package com.greglturnquist.learningspringboot.images;

import reactor.core.publisher.Flux;

import org.springframework.data.repository.Repository;

public interface CommentReaderRepository
	extends Repository<Comment, String> {

	Flux<Comment> findByImageId(String imageId);
}
// end::code[]
