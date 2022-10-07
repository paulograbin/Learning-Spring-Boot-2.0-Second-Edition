package com.greglturnquist.learningspringboot.images;

import lombok.Data;

import org.springframework.data.annotation.Id;

/**
 * @author Greg Turnquist
 */
@Data
public class Image {

	@Id
	private String id;

	private String name;

	public Image(String id, String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

}
