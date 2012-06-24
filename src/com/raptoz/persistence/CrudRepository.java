package com.raptoz.persistence;

import java.io.*;
import java.util.*;

public interface CrudRepository<T, ID extends Serializable> {
	
	void save(T entity);
	
	List<T> findAll();
	
	void deleteById(ID id);
	
	void delete(T entity);

	void deleteAll();
	
	T findOne(ID id);
	
	Long count();
	
	boolean exists(ID id);
	
}
