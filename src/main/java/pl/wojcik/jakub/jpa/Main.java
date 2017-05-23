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
		employee.setSalary(4000.0);
		
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		
		System.out.println("First name: " + employee.getFirstName());
		System.out.println("Last name: " + employee.getLastName());
		System.out.println("Salary: " + employee.getSalary());
		System.out.println("Tax: " + employee.getTax()); //nie pokaże działania triggera
		
		entityManager.refresh(employee); //pobranie aktualnego obiektu z bazy
		
		System.out.println("First name: " + employee.getFirstName());
		System.out.println("Last name: " + employee.getLastName());
		System.out.println("Salary: " + employee.getSalary());
		System.out.println("Tax: " + employee.getTax()); //teraz już pokaże działanie triggera
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
