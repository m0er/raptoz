package com.raptoz.toz;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.raptoz.persistence.*;

public interface TozMapper extends CrudRepository<Toz, Long> {
	
	@Insert("insert into toz(title, questioner_id, description) values(#{title}, #{questionerId}, #{description})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void save(Toz toz);
	
	@Select("select id, questioner_id as questionerId, title, description, create_timestamp as createTime, view_count as viewCount from toz")
	List<Toz> findAll();
	
	@Delete("delete from toz where id = #{id}")
	void deleteById(Long id);
	
	@Delete("delete from toz")
	void deleteAll();
	
	@Select("select id, questioner_id as questionerId, title, description, create_timestamp as createTime, view_count as viewCount from toz where id = #{tozId}")
	Toz findOne(Long tozId);
	
	@Select("select count(*) from toz")
	public Long count();
	
	@Delete("delete from toz where id = #{id} and questioner_id = #{questionerId}")
	public void delete(Toz entity);
	
	@Select("select count(*) from toz where id = #{id}")
	public boolean exists(Long id);
	
}
