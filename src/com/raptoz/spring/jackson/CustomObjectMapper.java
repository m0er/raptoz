package com.raptoz.spring.jackson;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import com.raptoz.spring.annotation.ServletComponent;

@ServletComponent
public class CustomObjectMapper extends ObjectMapper {
	
	public CustomObjectMapper() {
		CustomSerializerFactory sf = new CustomSerializerFactory();
        sf.addSpecificMapping(ObjectId.class, new ObjectIdSerializer());
        this.setSerializerFactory(sf);
	}
}
