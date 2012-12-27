package com.raptoz.spring.data.converter;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.raptoz.security.Role;

public class RoleTypeWriteConverter implements Converter<Role, DBObject> {

	@Override
	public DBObject convert(Role source) {
		DBObject dbo = new BasicDBObject();
		dbo.put("name", source.getName());
		dbo.put("value", source.getValue());
		return dbo;
	}

}
