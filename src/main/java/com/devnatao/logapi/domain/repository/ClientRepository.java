package com.devnatao.logapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devnatao.logapi.domain.model.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
	
	/**
	 * Will search on database an entity that
	 * have exactly the same value on column.
	 *	
	 * For return the database information "name" 
	 * must be the exactly same value.
	 * @param name
	 * @return entity
	 */
	ClientEntity findByName(String name);
	
	
	/**
	 * Will search on database an entity that
	 * contains the value of "name" variable.
	 * @param name
	 * @return entity
	 */
	ClientEntity findByNameContaining(String name);
}

 