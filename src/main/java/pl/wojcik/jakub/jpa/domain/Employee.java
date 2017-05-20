package pl.wojcik.jakub.jpa.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pracownicy1")
public class Employee {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "Imię")
	private String firstName;
	@Column(name = "Nazwisko")
	private String lastName;
	@Column(name = "Pensja", nullable = true)
	private double salary;

	@Embedded
	//@Column(name = "Adres") //tego nie można -> to wykrzaczy program; trzeba tak:
	@AttributeOverrides({
		@AttributeOverride(name = "locality", column = @Column(name = "Miasto")),
		@AttributeOverride(name = "zipCode", column = @Column(name = "Kod_pocztowy")),
		@AttributeOverride(name = "street", column = @Column(name = "Ulica")),
		@AttributeOverride(name = "streetNumber", column = @Column(name = "Nr_ulicy"))
	})
	private Address address;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}



}
