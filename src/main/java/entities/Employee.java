package entities;

import javax.persistence.*;


//on precise que cette classe est une entité
@Entity
//on lie la classe Book à la table appelé book
@Table(name="employee")
public class Employee {

	
	//Attributes
	private long id;
	private String name; 
	private String firstname;
	private String email;
    private long age;
    private String role;
    private String phone_number;
    private String address;
    
    //empty constructor
    public Employee() {
    }

    
    //getters & setters

    @Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
    
    public void setId(long id) {
    	this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}
    
    
}
