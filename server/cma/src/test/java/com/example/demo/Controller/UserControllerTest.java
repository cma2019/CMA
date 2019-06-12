package com.example.demo.Controller; 

import com.example.demo.Repository.UserRepository;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/** 
* UserController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 11, 2019</pre> 
* @version 1.0 
*/ 
public class UserControllerTest { 

    @Autowired
    private UserController userController;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addUser(@RequestParam(value = "username", required = false)String username, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "password2", required = false) String password2) 
* 
*/ 
@Test
public void testAddUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findUser(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password) 
* 
*/ 
@Test
public void testFindUser() throws Exception { 
//TODO: Test goes here...
    String str1="invalid email.";
    String str2="Sign in successfully.";
    String str3="Error email or password.";
    String test=userController.findUser("123","123456");
    assertEquals(str1,test);
} 


} 
