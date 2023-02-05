package com.greatlearning.employeemanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagement.dao.EmployeeRepository;
import com.greatlearning.employeemanagement.dao.RoleRepository;
import com.greatlearning.employeemanagement.dao.UserRepository;
import com.greatlearning.employeemanagement.entity.Employee;
import com.greatlearning.employeemanagement.entity.Role;
import com.greatlearning.employeemanagement.entity.User;
import com.greatlearning.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	// @Autowired
	// BCryptPasswordEncoder bcryptEncoder;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {

		Optional<Employee> result = employeeRepository.findById(theId);

		Employee employee = null;

		if (result.isPresent()) {
			employee = result.get();
		} else
			try {
				throw new RuntimeException("Did not find employee id - " + theId);
			} catch (Exception e) {
			}
		return employee;
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.saveAndFlush(employee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> getEmployeesCustomSortedByName(Direction direction) {
		return employeeRepository.findAll(Sort.by(direction, "firstName"));
	}

	// @Override
	// public List<Employee> sortByFirstNameAsc() {
	// return employeeRepository.findAllByOrderByFirstNameAsc();
	// }
	//
	// @Override
	// public List<Employee> sortByFirstNameDesc() {
	// return employeeRepository.findAllByOrderByFirstNameDesc();
	// }

	@Override
	public User saveUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

}
