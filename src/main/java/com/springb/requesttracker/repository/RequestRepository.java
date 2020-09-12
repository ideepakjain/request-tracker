/**
 * 
 */
package com.springb.requesttracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springb.requesttracker.entity.Request;

/**
 * The Interface RequestRepository.
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

}
