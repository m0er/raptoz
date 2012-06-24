package com.raptoz.user;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.raptoz.persistence.*;

public interface UserMapper extends CrudRepository<User, Long> {
	
	@Insert("INSERT INTO user(email, nickname, password, encode_profile_image) VALUES(#{email}, #{nickname}, #{password}, #{encodeProfileImage})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void save(User user);
	
	@Delete("DELETE FROM user")
	void deleteAll();
	
	@Delete("DELETE FROM user WHERE id = #{id}")
	void delete(User user);
	
	@Delete("DELETE FROM user WHERE id = #{id}")
	void deleteById(Long id);
	
	@Select("SELECT count(*) FROM user")
	Long count();
	
	@Select("SELECT id, email, nickname, encode_profile_image as encodeProfileImage FROM user WHERE id = #{id}")
	User findOne(Long id);
	
	@Select("SELECT id, email, nickname, encode_profile_image as encodeProfileImage FROM user WHERE email = #{email} AND password = #{password}")
	User findOneByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	@Select("SELECT count(*) FROM user where email = #{email} AND password = #{password}")
	public boolean isVerifyPassword(@Param("email") String email, @Param("password") String password);

	@Select("SELECT id, email, nickname, encode_profile_image as encodeProfileImage FROM user")
	List<User> findAll();
	
	@Select("SELECT count(*) FROM user where id = #{id}")
	public boolean exists(Long id);
	
	@Update("UPDATE user SET encode_profile_image = #{profileImage} WHERE id = #{id}")
	public void updateProfileImage(@Param("profileImage") String profileImage, @Param("id") Long id);
}
