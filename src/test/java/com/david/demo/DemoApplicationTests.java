package com.david.demo;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

import com.david.demo.user.UserEntity;

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

		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("david");
		UserEntity userEntity1 = userEntity;
		System.out.println(userEntity.getFirstName());
		System.out.println(userEntity1.getFirstName());

		userEntity.setFirstName("adam");

		System.out.println(userEntity.getFirstName());
		System.out.println(userEntity1.getFirstName());

		Map mp = new Hashtable();

		String exit = "ahoj";


	}

}
