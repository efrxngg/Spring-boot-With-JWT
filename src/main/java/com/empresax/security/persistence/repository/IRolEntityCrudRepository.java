package com.empresax.security.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresax.security.persistence.entity.RoleEntity;

public interface IRolEntityCrudRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(String name);

    @Query(value = "select * from findAllRolesByUsername(:username)", nativeQuery = true)
    List<RoleEntity> findAllRolesByUsername(@Param("username") String username);

}
