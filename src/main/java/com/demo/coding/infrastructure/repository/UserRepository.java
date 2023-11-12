package com.demo.coding.infrastructure.repository;

import com.demo.coding.domain.repository.UserRepositoryCustom;
import com.demo.coding.infrastructure.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserRepositoryCustom, JpaRepository<UserDAO, Long> {
}
