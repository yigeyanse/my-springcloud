package com.isoft.controller;

import com.isoft.dao.UserDao;
import com.isoft.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/user/save")
    public String save() {
        for (int i = 0; i <10 ; i++) {
            User user=new User();
            user.setName("test"+i);
            user.setCityId(1%2==0?1:2);
            user.setCreateTime(new Date());
            user.setSex(i%2==0?1:2);
            user.setPhone("11111111"+i);
            user.setEmail("xxxxx");
            user.setCreateTime(new Date());
            user.setPassword("eeeeeeeeeeee");
            userDao.save(user);
        }

        return "success";
    }

    @RequestMapping("/user/get/{id}")
    public User get(@PathVariable Long id) {
        User user =  userDao.get(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping("/user/sex/{sex}")
    public List<User> getBySex(@PathVariable Integer sex) {
        List<User> list =  userDao.getBySex(sex);
        System.out.println(list);
        return list;
    }
}
