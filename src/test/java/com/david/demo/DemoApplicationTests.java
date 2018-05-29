package com.david.demo;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.david.demo.user.User;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public final void contextLoads() {


		Map map = new HashMap();
		String[] pole = new String[2];
		String[] poles = {"a", "b", "c", "d"};
		System.out.println(poles.toString());
		int i = pole.hashCode();

		User user = new User();
		user.setFirstName("david");
		User user1 = user;
		System.out.println(user.getFirstName());
		System.out.println(user1.getFirstName());

		user.setFirstName("adam");

		System.out.println(user.getFirstName());
		System.out.println(user1.getFirstName());

		Map mp = new Hashtable();

		String exit = "ahoj";


	}

}
