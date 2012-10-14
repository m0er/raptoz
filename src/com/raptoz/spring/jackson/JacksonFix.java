
package com.raptoz.spring.jackson;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Component
public class JacksonFix {
	@Autowired RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	@Autowired private CustomObjectMapper objectMapper;

	@PostConstruct
	public void init() {
		List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
		
		for (HttpMessageConverter<?> messageConverter : messageConverters) {
			if (messageConverter instanceof MappingJacksonHttpMessageConverter) {
				MappingJacksonHttpMessageConverter m = (MappingJacksonHttpMessageConverter) messageConverter;
				m.setObjectMapper(objectMapper);
			}
		}
	}
	
}
