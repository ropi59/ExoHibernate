package manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.Employee;

public class BusinessManager {
	
	protected SessionFactory sessionFactory;

	protected void setup() {
		
		//on recupère la configuration d'hibernate pour la mettre dans registry
		final StandardServiceRegistry registry =
				new StandardServiceRegistryBuilder().configure().build();
		try {
			//on construit notre session si on arrive a se connecter a la BDD
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

//			//TEST
//			//on lance notre transaction
//			//on ouvre la session
//			Session session = sessionFactory.openSession();
//			session.beginTransaction();
//			session.getTransaction().commit();
//			session.close();
//			//
			
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.getStackTrace();
		}
	}
	
	protected void exit() {
		//on ferme la session
		sessionFactory.close();
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	protected void create() {
		// creation d'enmploye
		Employee employee1 = new Employee();
		employee1.setName("Dupond");
		employee1.setFirstname("Jean-Louis");
		employee1.setEmail("dupont.jl@mail.com");
		employee1.setAge(51L);
		employee1.setRole("CTO");
		employee1.setPhone_number("0601020304");
		employee1.setAddress("1 rue tapasalesavoir 59000 lille");
		
		Employee employee2 = new Employee();
		employee2.setName("Kelly");
		employee2.setFirstname("Dylan");
		employee2.setEmail("dylanlebg59@mail.com");
		employee2.setAge(16L);
		employee2.setRole("sous-fiffre");
		employee2.setPhone_number("0708090504");
		employee2.setAddress("51b impasse quicraint 59540 bousbecque");
		
		Employee employee3 = new Employee();
		employee3.setName("Jordan");
		employee3.setFirstname("Brenda");
		employee3.setEmail("lapbdu599@mail.com");
		employee3.setAge(19L);
		employee3.setRole("sous-fiffre");
		employee3.setPhone_number("0715264525");
		employee3.setAddress("17 rue de la soif 59000 lille");
		
		//on ouvre la session
		//on lance notre transaction
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//on enregistre l'employe en BDD
		session.save(employee3);
		//session.save(employee2);
		//on sauvegarde la transaction
		session.getTransaction().commit();
		session.close();
	}
	
	protected Employee read (long id) {
		//on ouvre la session
		Session session = sessionFactory.openSession();
		//on appelle l'employe par son id
		Employee employee = session.get(Employee.class, id);
		//affichage de l'employe récupéré
		System.out.println("Name : " + employee.getName());
		System.out.println("Firstname : " + employee.getFirstname());
		System.out.println("Email : " + employee.getEmail());
		System.out.println("Age : " + employee.getAge());
		System.out.println("Function : " + employee.getRole());
		System.out.println("Phone number : " + employee.getPhone_number());
		System.out.println("Address : " + employee.getAddress());
		return employee;
	}
	
//	protected Employee readAll() {
//		//on ouvre la session
//		Session session = sessionFactory.openSession();
//		
//	}
	
	protected void update(long id, Employee newEmployee) {
		Employee employee = this.read(id);
		if(newEmployee.getName() != null) {
			employee.setName(newEmployee.getName());
		}
		if(newEmployee.getFirstname() != null) {
			employee.setFirstname(newEmployee.getFirstname());
		}
		if(newEmployee.getEmail() != null) {
			employee.setEmail(newEmployee.getEmail());
		}
		//l'age peut etre null
		employee.setAge(newEmployee.getAge());	
		if(newEmployee.getRole() != null) {
			employee.setRole(newEmployee.getRole());
		}
		if(newEmployee.getPhone_number() != null) {
			employee.setPhone_number(newEmployee.getPhone_number());
		}
		if(newEmployee.getAddress() != null) {
			employee.setAddress(newEmployee.getAddress());
		}
		//on ouvre la session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//on update le livre en BDD
		session.update(employee);
		//on sauvegarde la transaction
		session.getTransaction().commit();
		session.close();
	}
		
	protected void delete(Employee employee) {
		//on ouvre la session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//on supprime un employee de la BDD
		session.delete(employee);
		//on enregistre les modifications
		session.getTransaction().commit();
		session.close();	
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	//MAIN
	public static void main(String[] args) {
		BusinessManager businessManager = new BusinessManager();
		//initialisation de la session
		businessManager.setup();
		//creation d'un employe
		businessManager.create();
		
		//nouvelles données d'un employe
//		Employee employee = new Employee();
//		employee.setAge(21);
//		//modification des données d'un employe
//		businessManager.update(2, employee);
//		businessManager.read(2);
		
		//supprimer un employe
//		Employee exEmployee = businessManager.read(3);
//		businessManager.delete(exEmployee);
		
		//voir les données d'un employe
		businessManager.read(1);
		
		//voir la liste de tous les employees
		//businessManager.readAll()
		
		businessManager.exit();
	}
}
