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
		employee.setFirstName("Paweł");
		employee.setLastName("Wojtaszko");
		employee.setSalary(3000.0);
		
		Address address = new Address();
		address.setLocality("Lublin");
		address.setStreet("Nadbystrzycka");
		address.setZipCode("21-030");
		address.setStreetNumber(9);
		
		employee.setAddress(address);
		
		entityManager.getTransaction().begin();
		//trzeba zapisać zarówno pracownika jak i sam adres!!! -> oddzielne encje
		entityManager.persist(address);
		entityManager.persist(employee);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}

}
