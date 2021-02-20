package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Employee> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery =
				currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results		
		return employees;
	}


	@Override
	public Employee findById(int theID) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee theEmployee = currentSession.get(Employee.class, theID);
		
		//return the result
		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);		
		
		//save employees
		currentSession.saveOrUpdate(theEmployee);
		
	}


	@Override
	public void deleteId(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);	
		
		//delete object with primary key
		Query theQuery= currentSession.createQuery("delete from Employee where id=:EmployeeId");
		
		theQuery.setParameter("EmployeeId", theId);
		theQuery.executeUpdate();
		
		
	}

}







