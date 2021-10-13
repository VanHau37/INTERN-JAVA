package com.nvh.intern.Repository;

import org.springframework.data.repository.CrudRepository;

import com.nvh.intern.Entity.User;

public interface UsersRepository extends CrudRepository<User, Long>{

	

}
