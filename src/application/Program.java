package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int option =0;
		UserDao userDao = DaoFactory.createUserDao();

		do {
			System.out.println("=== CRUD ===");
			System.out.println("1) Register User");
			System.out.println("2) Edit User");
			System.out.println("3) List Users");
			System.out.println("4) Search User");
			System.out.println("5) Delete User");
			System.out.println("6) Exit");

			System.out.print("> ");
			option = sc.nextInt();
			sc.nextLine();

			switch (option) {
			case 1:
				System.out.println("\n=== REGISTER USER ===\n");
				System.out.print("User name: ");
				String name = sc.nextLine();
				System.out.print("User email: ");
				String email = sc.nextLine();
				User user = new User(null, name, email);
				userDao.insert(user);
				System.out.println("\nInserted! New id = " + user.getId() + " | " + user.getName() + " | " + user.getEmail() + "\n");
				break;
			case 2:
				System.out.println("\n=== EDIT USER ===\n");
				break;
			case 3:
				System.out.println("\n=== LIST USERS ===\n");
				break;
			case 4:
				System.out.println("\n=== SEACH USER ===\n");
				break;
			case 5:
				System.out.println("\n=== DELETE USER ===\n");
				break;
			case 6:
				System.out.println("\nEXITING...\n");
				break;
			}
		} while (option != 6);
	}
}
