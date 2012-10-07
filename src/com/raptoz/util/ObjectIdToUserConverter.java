package com.raptoz.util;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Set;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.web.bind.annotation.PathVariable;

import com.raptoz.user.UserRepository;

public class ObjectIdToUserConverter implements ConditionalGenericConverter {
	@Autowired UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		return userRepository.findOne(ObjectId.massageToObjectId(source));
	}

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		logger.info(sourceType.toString());
		logger.info(targetType.toString());
		
		Annotation pathVariable = targetType.getAnnotation(PathVariable.class);
		
		if (pathVariable != null && ((PathVariable) targetType.getAnnotation(PathVariable.class)).value().equals("id"))
			return true;
		
		return false;
	}

}
