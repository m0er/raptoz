package com.raptoz.spring.annotation;

import java.lang.annotation.*;

import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ServletComponent {
	
	String value() default "";
	
}
