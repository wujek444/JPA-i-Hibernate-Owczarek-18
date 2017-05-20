package pl.wojcik.jakub.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.wojcik.jakub.jpa.domain.Employee;

public class FindAndModify {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// szukamy w bazie pracownika o identyfikatorze 1
		entityManager.getTransaction().begin();
		// jeżeli identyfikator jest typu long, to musimy dokładnie określić ten
		// typ tutaj:
		Employee employee = entityManager.find(Employee.class, 1L);
		// przed modyfikacją:
		System.out.println("Imię: " + employee.getFirstName());
		System.out.println("Nazwisko: " + employee.getLastName());
		System.out.println("Pensja: " + employee.getSalary());
		// modyfikacja pracownika:
		employee.setSalary(10000000);
		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();

	}

}
