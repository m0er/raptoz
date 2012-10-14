package com.raptoz.spring.converter;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.PathVariable;

import com.raptoz.user.User;
import com.raptoz.user.UserRepository;

public class ObjectIdToUserConverter implements ConditionalGenericConverter {
	@Autowired UserRepository userRepository;
	
	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(String.class, User.class));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		return userRepository.findOne(ObjectId.massageToObjectId(source));
	}

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		Annotation pathVariable = targetType.getAnnotation(PathVariable.class);
		
		if (pathVariable != null && targetType.getType().isAnnotationPresent(Document.class))
			return true;
		
		return false;
	}

}
