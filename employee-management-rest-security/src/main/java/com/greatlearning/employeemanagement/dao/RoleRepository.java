package com.greatlearning.employeemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeemanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
