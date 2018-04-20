package com.uttara.beeresh.Service;

import java.util.ArrayList;
import java.util.List;

import com.uttara.beeresh.Model.User;

public class UserService {

	private static List<User> list;

	
	
	static
	{
		list=startAppUsers();
		
	}
	
	public UserService() {
		
	}

	public User fingUserById(Long id) {

		for (User u : list) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public List<User> getAllUsers() {

		return list;

	}
	
	public void addUser(User user)
	{
		
		list.add(user);
	}
	
	public static List<User> startAppUsers()
	{
		System.out.println(".........");
	ArrayList<User>	list = new ArrayList<User>();
		list.add(new User(1, "beer", 25, 12000));
		list.add(new User(2, "beer1", 26, 100000));
		list.add(new User(3, "beer2", 27, 120000));
		return list;
	}

}
