package com.isoft.homepage.service.impl;

import com.isoft.homepage.CourseInfo;
import com.isoft.homepage.CourseInfosRequest;
import com.isoft.homepage.UserInfo;
import com.isoft.homepage.client.CourseClient;
import com.isoft.homepage.dao.HomepageUserCourseDao;
import com.isoft.homepage.dao.HomepageUserDao;
import com.isoft.homepage.entity.HomepageUser;
import com.isoft.homepage.entity.HomepageUserCourse;
import com.isoft.homepage.service.IUserService;
import com.isoft.homepage.vo.CreateUserRequest;
import com.isoft.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1>用户相关服务实现</h1>
 * Created by Qinyi.
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    /** HomepageUser Dao */
    private final HomepageUserDao homepageUserDao;

    /** HomepageUserCourse Dao */
    private final HomepageUserCourseDao homepageUserCourseDao;

    private final CourseClient courseClient;

    @Autowired
    public UserServiceImpl(HomepageUserDao homepageUserDao, HomepageUserCourseDao homepageUserCourseDao,
                           CourseClient courseClient) {
        this.homepageUserDao = homepageUserDao;
        this.homepageUserCourseDao = homepageUserCourseDao;
        this.courseClient = courseClient;
    }

    @Override
    public UserInfo createUser(CreateUserRequest request) {

        if (!request.validate()) {
            return UserInfo.invalid();
        }

        HomepageUser oldUser = homepageUserDao.findByUsername(request.getUsername());
        if (null != oldUser) {
            return UserInfo.invalid();
        }

        HomepageUser newUser = homepageUserDao.save(new HomepageUser(
                request.getUsername(), request.getEmail()
        ));

        return new UserInfo(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    @Override
    public UserInfo getUserInfo(Long id) {

        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if (!user.isPresent()) {
            return UserInfo.invalid();
        }

        HomepageUser curUser = user.get();
        return new UserInfo(curUser.getId(), curUser.getUsername(), curUser.getEmail());
    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {

        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if (!user.isPresent()) {
            return UserCourseInfo.invalid();
        }

        HomepageUser homepageUser = user.get();
        UserInfo userInfo = new UserInfo(homepageUser.getId(), homepageUser.getUsername(),
                homepageUser.getEmail());

        List<HomepageUserCourse> userCourses = homepageUserCourseDao.findAllByUserId(id);
        if (CollectionUtils.isEmpty(userCourses)) {
            return new UserCourseInfo(userInfo, Collections.emptyList());
        }

        List<CourseInfo> courseInfos = courseClient.getCourseInfos(
                new CourseInfosRequest(
                        userCourses.stream().map(HomepageUserCourse::getCourseId).collect(Collectors.toList())
                )
        );

        return new UserCourseInfo(userInfo, courseInfos);
    }

    private void test() throws InterruptedException {
        RedissonClient client = Redisson.create();
        RList<String> list = client.getList("myList");
        list.clear();
        list.add("bingo");
        list.add("yanglbme");
        list.add("https://github.com/yanglbme");
        list.remove(-1);

        boolean contains = list.contains("yanglbme");
        System.out.println("List size: " + list.size());
        System.out.println("Is list contains name 'yanglbme': " + contains);
        list.forEach(System.out::println);
        client.shutdown();


        // RLock 继承了 java.util.concurrent.locks.Lock 接口
        RLock lock = client.getLock("lock");
        lock.lock();
        System.out.println("lock acquired");
        Thread t = new Thread(() -> {
            RLock lock1 = client.getLock("lock");
            lock1.lock();
            System.out.println("lock acquired by thread");
            lock1.unlock();
            System.out.println("lock released by thread");
        });
        t.start();
        t.join(1000);
        lock.unlock();
        System.out.println("lock released");
        t.join();
        client.shutdown();
    }
}
