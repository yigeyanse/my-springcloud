package com.isoft.dao;

import com.isoft.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDao {

    /**
     * 保存
     */
    void save(Address address);

    /**
     * 查询
     * @param id
     * @return
     */
    Address get(Long id);
}
