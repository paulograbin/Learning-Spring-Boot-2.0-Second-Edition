// tag::code[]
package com.greglturnquist.learningspringboot.images;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Comment {

	@Id private String id;
	private String imageId;
	private String comment;

}
// end::code[]