package pl.wojcik.jakub.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.wojcik.jakub.jpa.domain.Employee;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Employee employee = new Employee();
		employee.setFirstName("Jakub");
		employee.setLastName("Wójcik");
		employee.setSalary(3000.0);
		employee.setLocality("Świdnik");
		employee.setZipCode("21-040");
		employee.setStreet("Spadochroniarzy");
		employee.setStreetNumber(1);
		
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
