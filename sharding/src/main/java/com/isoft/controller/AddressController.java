package com.isoft.controller;

import com.isoft.dao.AddressDao;
import com.isoft.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressDao addressDao;

    @RequestMapping("/address/save")
    public String save() {
        for (int i = 0; i <10 ; i++) {
            Address address=new Address();
            address.setCode("code_"+i);
            address.setName("name_"+i);
            address.setPid(i+"");
            address.setType(0);
            address.setLit(i%2==0?1:2);
            addressDao.save(address);
        }

        return "success";
    }

    @RequestMapping("/address/get/{id}")
    public Address get(@PathVariable Long id) {
        return addressDao.get(id);
    }
}
