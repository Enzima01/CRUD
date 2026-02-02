package application;

import java.util.List;
import java.util.Scanner;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int option = 0;
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

			try {
				switch (option) {
				case 1:
					System.out.println("\n=== REGISTER USER ===");
					System.out.print("User name: ");
					String name = sc.nextLine().trim();
					System.out.print("User email: ");
					String email = sc.nextLine().trim();
					if (!email.contains("@")) {
						System.out.println("\nInvalid email!\n");
						break;
					}
					User user = new User(null, name, email);
					userDao.insert(user);
					System.out.println("\nNew user inserted!: " + user.getId() + " | " + user.getName() + " | "
							+ user.getEmail() + "\n");
					break;
				case 2:
					System.out.println("\n=== EDIT USER ===");
					System.out.print("Enter user id to edit data: ");
					int id = sc.nextInt();
					sc.nextLine();

					User existingUser = userDao.findById(id);

					if (existingUser == null) {
						System.out.println("\nUser not found!\n");
						break;
					}

					System.out.print("Enter new user name (Leave blank if you don't want to edit!): ");
					name = sc.nextLine().trim();
					System.out.print("Enter new user email (Leave blank if you don't want to edit!): ");
					email = sc.nextLine().trim();

					String newName = name.isEmpty() ? existingUser.getName() : name;
					String newEmail = email.isEmpty() ? existingUser.getEmail() : email;

					if (!newEmail.contains("@")) {
						System.out.println("\nInvalid email!\n");
						break;
					}
					if (newName.equals(existingUser.getName()) && newEmail.equals(existingUser.getEmail())) {
						System.out.println("\nNo changes detected.\n");
						break;
					}
					user = new User(id, newName, newEmail);
					userDao.update(user);
					System.out.println("\nUser Edited!: " + user.getId() + " | " + user.getName() + " | "
							+ user.getEmail() + "\n");
					break;
				case 3:
					System.out.println("\n=== LIST USERS ===");
					List<User> list = userDao.findAll();
					for (User obj : list) {
						System.out.println(obj);
					}
					System.out.println();
					break;
				case 4:
					System.out.println("\n=== SEARCH USER ===");
					System.out.print("Enter user id: ");
					id = sc.nextInt();
					user = userDao.findById(id);
					if (user == null) {
						System.out.println("\nUser not found!\n");
					} else {
						System.out.println(user + "\n");
					}
					break;
				case 5:
					System.out.println("\n=== DELETE USER ===\n");
					System.out.print("Enter user id to delete: ");
					id = sc.nextInt();
					userDao.deleteById(id);
					System.out.println("User Deleted!" + "\n");
					break;
				case 6:
					System.out.println("\nEXITING...\n");
					break;
				default:
					System.out.println("\nInvalid option!\n");
				}
			} catch (DbException e) {
				System.out.println("\nError: " + e.getMessage() + "\n");
			}
		} while (option != 6);

		sc.close();
	}
}
