package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		// create a query
		Query theQuery =
				entityManager.createQuery("from Employee");
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results		
		return employees;
	}

	@Override
	public Employee findById(int theID) {

		// get the employee
		Employee theEmployee = entityManager.find(Employee.class, theID);
		
		//return the result
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// save the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteId(int theId) {
		//delete object with primary key
		Query theQuery= entityManager.createQuery("delete from Employee where id=:EmployeeId");
		
		theQuery.setParameter("EmployeeId", theId);
		theQuery.executeUpdate();

	}

}
