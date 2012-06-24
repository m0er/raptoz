package com.raptoz.participant;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.raptoz.persistence.*;
import com.raptoz.toz.*;

public interface TozParticipantMapper extends CrudRepository<TozParticipant, Long> {
	
	@Insert("insert into toz_participant(user_id, toz_id, comment) values(#{userId}, #{tozId}, #{comment})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void save(TozParticipant entity);
	
	@Select("select id, user_id as userId, toz_id as tozId, comment, join_timestamp as joinDate from toz_participant")
	List<TozParticipant> findAll();
	
	@Select("select id, user_id as userId, toz_id as tozId, comment, join_timestamp as joinDate from toz_participant where toz_id = #{tozId}")
	List<TozParticipant> findAllByTozId(Long tozId);
	
	@Delete("delete from toz_participant where id = #{id}")
	void deleteById(Long id);
	
	@Select("select count(*) from toz_participant")
	Long count();
	
	@Select("select count(*) from toz_participant where toz_id = #{tozId}")
	Long countByTozId(Long tozId);
	
	@Delete("delete from toz_participant where id = #{id} and toz_id = #{tozId} and user_id = #{userId}")
	void delete(TozParticipant entity);
	
	@Delete("delete from toz_participant")
	void deleteAll();
	
	@Select("select count(*) from toz_participant where id = #{id}")
	boolean exists(Long id);
	
	@Select("select id, user_id as userId, toz_id as tozId, comment, join_timestamp as joinDate from toz_participant where id = #{id}")
	TozParticipant findOne(Long id);
	
	@Select("select id, user_id as userId, toz_id as tozId, comment, join_timestamp as joinDate from toz_participant where user_id = #{userId}")
	List<TozParticipant> findAllByUsrId(Long userId);
	
}
