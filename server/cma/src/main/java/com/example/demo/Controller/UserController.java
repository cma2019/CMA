package com.example.demo.Controller;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/cma/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addUser(@RequestParam(value = "username", required = false)String username,@RequestParam(value = "email", required = false) String email,@RequestParam(value = "password", required = false) String password,@RequestParam(value = "password2", required = false) String password2){
        /*System.out.println(username);
        System.out.println(email);
        System.out.println(password);
        System.out.println(password2);*/
        if(userRepository.findByEmail(email)!=null)
            return "Email address exists.";
        if(password.equals(password2)==false)
            return "The two passwords differ.";
        User user=new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "Sign up successfully.";
    }
    @GetMapping(path="/find")
    public @ResponseBody String findUser(@RequestParam(value = "email", required = false) String email,@RequestParam(value = "password", required = false) String password){
        User u=new User();
        if(userRepository.findByEmail(email)==null)
            return "invalid email.";
        else{
            u=userRepository.findByEmail(email);
            if(u.getPassword().equals(password)==true)
                return "Sign in successfully.";
            else
                return "Error email or password.";
        }
    }

}
