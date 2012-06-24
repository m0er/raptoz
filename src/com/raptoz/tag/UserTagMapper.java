package com.raptoz.tag;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.raptoz.persistence.*;

public interface UserTagMapper extends CrudRepository<Tag, Long> {
	
	@Insert("insert into user_tag(user_id, value) values(#{ownerId}, #{value})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void save(Tag tag);
	
	@Delete("delete from user_tag where id = #{id}")
	void deleteById(Long id);
	
	@Delete("delete from user_tag where id = #{id}")
	void delete(Tag tag);
	
	@Delete("delete from user_tag")
	void deleteAll();
	
	@Delete("delete from user_tag where user_id = #{ownerId}")
	void deleteAllByOwnerId(Long ownerId);
	
	@Select("select id, user_id as ownerId, value from user_tag")
	List<Tag> findAll();
	
	@Select("select id, user_id as ownerId, value from user_tag where value = #{value}")
	List<Tag> findAllByValue(String value);
	
	@Select("select id, user_id as ownerId, value from user_tag where id = #{id}")
	Tag findOne(Long id);
	
	@Select("select id, user_id as ownerId, value from user_tag where user_id = #{ownerId}")
	List<Tag> findAllByOwnerId(Long ownerId);
	
	@Select("select count(*) from user_tag")
	Long count();
	
	@Select("select count(*) from user_tag where id = #{id}")
	boolean exists(Long id);

}
