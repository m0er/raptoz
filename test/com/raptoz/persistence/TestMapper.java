package com.raptoz.persistence;

import org.apache.ibatis.annotations.*;

public interface TestMapper {
	
	@Select("select now()")
	String getTime();
	
	/*
	@Select("SELECT * FROM testUser WHERE id = #{userId}")
	User getUser(@Param("userId") String userId);
	*/
	
	/*
	@Select("select id, make, model, model_year as \"year\" from automobiles where id = #{id}")
	Automobile selectAutomobile(final int id);
	
	@Insert("insert into automobiles(id,make,model,model_year) 
	    values (#{id}, #{make}, #{model}, #{year})")
	void insertAutomobile(final Automobile arg);
	
	@Delete("delete from automobiles")
	void deleteAll();
	*/
}
