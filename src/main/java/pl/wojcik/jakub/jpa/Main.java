package pl.wojcik.jakub.jpa;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pl.wojcik.jakub.jpa.domain.Employee;

public class Main {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {

		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();

		addEmployees();
		// //budujemy zapytanie z użyciem EntityManagera
		// TypedQuery<Employee> query = entityManager.createQuery("select e from
		// Employee e where e.lastName = 'Pająk'", Employee.class);
		// //wiemy, że zapytanie to zwróci jednego pracownika, więc korzystamy z
		// metody:
		// Employee singleEmployee = query.getSingleResult();
		// System.out.println(singleEmployee);

		// //teraz budujemy zapytanie, które zwróci większą liczbę wyników:
		// TypedQuery<Employee> multipleResultsQuery =
		// entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName
		// = 'Mateusiak'", Employee.class);
		// List<Employee> resultList = multipleResultsQuery.getResultList();
		// resultList.forEach(x -> System.out.println(x));

		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Podaj nazwisko: ");
		// String userIn = scanner.nextLine();
		// //budujemy następne zapytanie z większą ilością rezultatów:
		// entityManager.createQuery("SELECT e.firstName FROM Employee e WHERE
		// e.lastName = '"+ userIn + "'", String.class)
		// .getResultList().forEach(x -> System.out.println(x));

		// korzystamy teraz z zapytania nietypowanego: UWAGA -> NIEBEZPIECZNE PODEJŚCIE!
		Query query = entityManager
				.createQuery("select concat(e.firstName, ' ', e.lastName), e.salary * 0.2 from Employee e ");
		// aby przemieszczać się po elementach listy wynikowej potrzebujemy
		// iteratora listy:
		Iterator<?> iterator = query.getResultList().iterator(); //iterator nie wie co będzie zwracał
		while (iterator.hasNext()) {
			Object[] item = (Object[]) iterator.next();
			String name = (String) item[0];
			double tax = (double) item[1];
			System.out.println(name + "has to pay " + tax);

		}

		entityManager.close();
		entityManagerFactory.close();
	}

	private static void addEmployee(String firstName, String lastName, double salary) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setSalary(salary);

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}

	private static void addEmployees() {
		addEmployee("Karol", "Mateusiak", 2633);
		addEmployee("Marika", "Bednarek", 2345);
		addEmployee("Jan", "Mateusiak", 9876);
		addEmployee("Daria", "Kowalska", 2300);
		addEmployee("Monika", "Ucińska", 4267);
		addEmployee("Ernest", "Pająk", 6444);
		addEmployee("Kamil", "Stępień", 4234);
		addEmployee("Przemek", "Maciejewski", 6444);
		addEmployee("Robert", "Woźniak", 5555);
		addEmployee("Agnieszka", "Nowak", 2300);
		addEmployee("Angelika", "Bednarska", 1000);
	}

}
