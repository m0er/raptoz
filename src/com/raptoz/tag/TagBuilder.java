package com.raptoz.tag;

import java.util.ArrayList;
import java.util.List;

public class TagBuilder {
	private List<Tag> tags = new ArrayList<>();
	
	public TagBuilder add(String... values) {
		for (String value : values)
			tags.add(new Tag(value));
		
		return this;
	}
	
	public List<Tag> build() {
		return tags;
	}
	
}
