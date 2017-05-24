package pl.wojcik.jakub.jpa;

import java.util.ArrayList;
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
		// tutaj jest przykład zapytania tworzonego "na sztywno" (3000 jest
		// niezmienne)
		// String queryText = "SELECT e FROM Employee e WHERE e.salary >
		// 3000.0";

		// tutaj mamy zapytanie z wykorzystaniem parametru nazywanego minSalary:
		// wykonanie zapytania:
		TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary > :minSalary",
				Employee.class);
		// ustawienie parametru minSalary:
		query.setParameter("minSalary", 5000.0);

		// pobranie i wyświetlenie wyników zapytania:
		List<Employee> resultList = query.getResultList();
		resultList.forEach(x -> System.out.println(x));
		System.out.println("111111111111111111111111111111111111111111111111111111111111");

		// tutaj wykorzystamy zapytanie z parametrem indeksowanym (?1 i ?2 to
		// indeksy):
		TypedQuery<Employee> queryWithIndexParameter = entityManager
				.createQuery("select e from Employee e where e.salary > ?1 and e.salary < ?2", Employee.class);
		//trzeba uważać na typy liczb!!!:
		queryWithIndexParameter.setParameter(1, 2000.0);
		queryWithIndexParameter.setParameter(2, 3000.0);
		
		List<Employee> resultList2 = queryWithIndexParameter.getResultList();
		resultList2.forEach(x -> System.out.println(x));
		
		System.out.println("22222222222222222222222222222222222222222222222222222222222222");
		//zapytanie z listą (parametr 'names' będzie listą, przechowującą nazwiska):
		TypedQuery<Employee> queryWithList = entityManager
				.createQuery("select e from Employee e where e.lastName in :names", Employee.class);
		List<String> names = new ArrayList<>();
		names.add("Mateusiak");
		names.add("Bednarek");
		queryWithList.setParameter("names", names); //podajemy listę nazwisk jako parametr
		queryWithList.getResultList().forEach(System.out::println);
		
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
