package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.Item;
import com.example.demo.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
        * @author lyt
        * 返回code   200→成功
        *            5xx→异常
        */
@Controller
@RequestMapping(path="/cma/TestAbility")//定义接口
public class ItemController {
    @Autowired
    private ItemRepository ItemRepository;

    @PostMapping(path="/addOneItem")//添加信息
    public @ResponseBody JSONObject addOneItem(@RequestParam(value="year",required = false)Long year,
                          @RequestParam(value = "productionName",required = false)String productionName,
                          @RequestParam(value = "ability",required = false)String ability,
                          @RequestParam(value = "referrence",required = false)String referrence){
        JSONObject json=new JSONObject();
        Item newItem=new Item();
        newItem.setYear(year);
        newItem.setProductionName(productionName);
        newItem.setAbility(ability);
        newItem.setReferrence(referrence);
        ItemRepository.save(newItem);
        json.put("code",200);
        json.put("msg","添加成功");
        return json;
    }

    @GetMapping(path="/getAllItem")//获取所有信息
    public @ResponseBody JSONObject getAllItem(@RequestParam(value="year",required = false)Long year){
        JSONObject json=new JSONObject();
        /*if(ItemRepository.findAllByYear(year)==null){
            json.put("code",500);
            json.put("msg","信息不存在");
        }
        else
        {*/
            json.put("code",200);
            json.put("msg","获取成功");
            json.put("data",ItemRepository.findAllByYear(year));
        //}
        return json;
    }

    @GetMapping(path="/getOneItem")//获取某个信息
    public @ResponseBody JSONObject getOneItem(@RequestParam(value = "id",required = false)Long id){
        JSONObject json=new JSONObject();
        if(ItemRepository.findById(id)==null){//判断是否为空
            json.put("code",500);
            json.put("msg","信息不存在");
        }
        else{
            json.put("code",200);
            json.put("msg","获取成功");
            json.put("data",ItemRepository.findById(id));
        }
        return json;
    }

    @PostMapping(path="/deleteOneItem")//删除信息
    public @ResponseBody JSONObject deleteOneItem(@RequestParam(value = "id",required = false)Long id){
        JSONObject json=new JSONObject();
        if(ItemRepository.findById(id)==null){//判断是否为空
            json.put("code",500);
            json.put("msg","信息不存在");
        }
        else
        {
            ItemRepository.deleteById(id);
            json.put("code",200);
            json.put("msg","删除成功");
        }
        return json;
    }
}
