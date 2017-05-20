package pl.wojcik.jakub.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.wojcik.jakub.jpa.domain.Address;
import pl.wojcik.jakub.jpa.domain.Employee;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Employee employee = new Employee();
		employee.setFirstName("Sylwia");
		employee.setLastName("Drwal");
		employee.setSalary(4000.0);
		
		Address address = new Address();
		address.setLocality("Świdnik");
		address.setStreet("Kosynierów");
		address.setZipCode("21-040");
		address.setStreetNumber(5);
		
		employee.setAddress(address);
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
