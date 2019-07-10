package com.example.demo.Repository;

import com.example.demo.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findAllByYear(long year);//根据年份查找所有信息
    Item findByYear(long year);//根据年份查找单个信息
}
