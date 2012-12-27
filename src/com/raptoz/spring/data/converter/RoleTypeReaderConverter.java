package com.raptoz.spring.data.converter;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;
import com.raptoz.security.Role;

public class RoleTypeReaderConverter implements Converter<DBObject, Role> {

	@Override
	public Role convert(DBObject source) {
		return Role.valueOf(((String) source.get("name")).toUpperCase());
	}

}
