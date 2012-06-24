package com.raptoz.tag;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.raptoz.persistence.*;

public interface TozTagMapper extends CrudRepository<Tag, Long> {
	
	@Insert("insert into toz_tag(toz_id, value) values(#{ownerId}, #{value})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void save(Tag tag);
	
	@Delete("delete from toz_tag where id = #{id}")
	void delete(Tag tag);
	
	@Delete("delete from toz_tag where id = #{id}")
	void deleteById(Long id);
	
	@Select("select id, toz_id as ownerId, value from toz_tag")
	List<Tag> findAll();
	
	@Select("select id, toz_id as ownerId, value from toz_tag where id = #{id}")
	Tag findOne(Long id);
	
	@Select("select id, toz_id as ownerId, value from toz_tag where toz_id = #{ownerId}")
	List<Tag> findAllByOwnerId(Long ownerId);

	@Select("select id, toz_id as ownerId, value from toz_tag where value = #{value}")
	List<Tag> findAllByValue(String value);

	@Delete("delete from toz_tag")
	void deleteAll();
	
	@Delete("delete from toz_tag where toz_id=#{ownerId}")
	void deleteAllByOwnerId(Long ownerId);
	
	@Select("select count(*) from toz_tag")
	Long count();
	
	@Select("select count(*) from toz_tag where id = #{id}")
	boolean exists(Long id);
}
