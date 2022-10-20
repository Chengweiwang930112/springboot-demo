package com.check24.coding.infrastructure.repository;

import com.check24.coding.domain.repository.UserRepositoryCustom;
import com.check24.coding.infrastructure.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserRepositoryCustom, JpaRepository<UserDAO, Long> {
}
