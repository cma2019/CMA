package com.example.demo.Controller;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
//import net.minidev.json.JSONObject;
import com.google.appengine.repackaged.com.google.common.hash.HashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.framework.Aes;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(path="/cma/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private String tempCode;
    private String tempUserName;
    String tempData1="12345";
    Aes aes=new Aes();
    //private String userKey;
    @PostMapping(path = "/add")
    public @ResponseBody JSONObject addUser(/*@RequestParam(value = "username", required = false) String username, */
            /*@RequestParam(value = "password", required = false) String password,*/
            /*@RequestParam(value = "password2", required = false) String password2*/
            @RequestParam(value = "data", required = false) String data) {
        JSONObject json = new JSONObject();
        /*if (userRepository.findByUsername(username) != null) {
            json.put("code", 210);
            json.put("msg", "已存在用户");
            return json;
        }*/
        //return "Username address exists.";
        System.out.println(("add in"));
        System.out.println(tempCode);
        System.out.println(data);
        //Aes aes=new Aes();


        //aes.changeKey(tempCode);
        String secretData3=aes.encrypt(tempData1);
        System.out.println("add换密码后:"+secretData3);


        String tempData=aes.decrypt(data);
        System.out.println("解密后:"+tempData);
        JSONObject obj=JSONObject.parseObject(tempData);
        String password=obj.getString("password");
        String password2=obj.getString("password2");
        if (password.equals(password2) == false) {
            json.put("code", 210);
            json.put("msg", "两次密码不匹配");
            return json;
        }
        //return "The two passwords differ.";
        User user = new User();
        /*user.setUsername(username);*/
        System.out.println(tempUserName);
        user.setUsername(tempUserName);
        user.setPassword(password);
        boolean[] temp = new boolean[20];
        for (int i = 0; i < 20; i++)
            temp[i] = false;
        user.setPermission(temp);
        userRepository.save(user);
        json.put("code", 200);
        json.put("msg", "注册成功");
        json.put("permission", user.getPermission());
        return json;
    }
    @GetMapping(path="/find")
    public @ResponseBody JSONObject findUser(@RequestParam(value = "username", required = false) String username,@RequestParam(value = "password", required = false) String password){
        JSONObject json=new JSONObject();
        User u=new User();
        if(userRepository.findByUsername(username)==null)
        {
            json.put("code",210);
            json.put("msg","不存在的用户");
        }
        else{
            u=userRepository.findByUsername(username);
            if(u.login(password)==true)
            {
                json.put("code",200);
                json.put("msg","登录成功");
            }
            else
            {
                json.put("code",210);
                json.put("msg","密码错误");
            }
        }
        return json;
    }

    //lyt add
    @PostMapping(path = "/getCode")
    public @ResponseBody JSONObject getCode(@RequestParam(value = "username", required = false) String username) {
        JSONObject json = new JSONObject();
        if (userRepository.findByUsername(username) != null) {
            json.put("code", 210);
            json.put("msg", "已存在用户");
            return json;
        }
        System.out.println("get Code in");
        //Aes aes=new Aes();
        tempCode=aes.encrypt(username);
        tempCode=tempCode.substring(0,16);

        String secretData1=aes.encrypt(tempData1);
        System.out.println("换密码前:"+secretData1);
        aes.changeKey(tempCode);
        String secretData2=aes.encrypt(tempData1);
        System.out.println("换密码后:"+secretData2);


        json.put("code",200);
        json.put("msg","Roger");
        tempUserName=username;
        System.out.println("username:"+username);
        System.out.println("新密钥:"+tempCode);
        return json;
    }

    @GetMapping(path = "getOne")
    public @ResponseBody JSONObject getOne(@RequestParam(value="username",required = false)String username){
        JSONObject json=new JSONObject();
        if(userRepository.existsByUsername(username)==false)
        {
            json.put("code",210);
            json.put("msg","不存在的用户");
            json.put("data",null);
        }
        else{
            User user=userRepository.findByUsername(username);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data1",user.Permissions());
            json.put("data2",user.getPermission());
            json.put("length",user.Permissions().size());
        }
        return json;
    }

    @PostMapping(path = "modifyOne")
    public @ResponseBody JSONObject modifyOne(@RequestParam(value = "username",required = false)String username, @RequestParam(value = "permission",required = false) boolean[] permission){
        JSONObject json=new JSONObject(new LinkedHashMap());
        if(userRepository.existsByUsername(username)==false){
            json.put("code",210);
            json.put("msg","不存在的用户");
            json.put("data",null);
        }
        else{
            User user=userRepository.findByUsername(username);
            System.out.println(permission);
            user.setPermission(permission);
            userRepository.save(user);
            json.put("code",200);
            json.put("msg","成功");
            json.put("data",null);
        }
        return json;
    }

    @GetMapping(path = "getAll")
    public @ResponseBody JSONObject getAll(){
        JSONObject json=new JSONObject();
        List<User> list=userRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUsername().equals("admin")){
                list.remove(i);
            }
        }
        json.put("code",200);
        json.put("msg","成功");
        json.put("data",list);
        return json;
    }
}
