package com.isoft.homepage.service;

import com.isoft.homepage.UserInfo;
import com.isoft.homepage.vo.CreateUserRequest;
import com.isoft.homepage.vo.UserCourseInfo;

/**
 * <h1>用户相关服务接口定义</h1>
 * Created by Qinyi.
 */
public interface IUserService {

    /**
     * <h2>创建用户</h2>
     * @param request {@link CreateUserRequest}
     * @return {@link UserInfo} 用户基本信息
     * */
    UserInfo createUser(CreateUserRequest request);

    /**
     * <h2>获取用户的基本信息</h2>
     * @param id 用户 id
     * @return {@link UserInfo} 用户基本信息
     * */
    UserInfo getUserInfo(Long id);

    /**
     * <h2>获取用户课程信息</h2>
     * @param id 用户 id
     * @return {@link UserCourseInfo}
     * */
    UserCourseInfo getUserCourseInfo(Long id);
}
