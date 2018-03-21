/**
 * 
 */
package com.my.experiments.springbootdockerpost.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.experiments.springbootdockerpost.domain.User;

/**
 * @author tejansh_rana
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
