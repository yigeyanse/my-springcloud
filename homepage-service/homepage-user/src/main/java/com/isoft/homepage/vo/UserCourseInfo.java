package com.isoft.homepage.vo;

import com.isoft.homepage.CourseInfo;
import com.isoft.homepage.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * <h1>用户课程信息对象定义</h1>
 * Created by Qinyi.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseInfo {

    private UserInfo userInfo;
    private List<CourseInfo> courseInfos;

    public static UserCourseInfo invalid() {

        return new UserCourseInfo(
                //LocalDate.of(2001,3,23)
                //查找身高在1.8米及以上的男生
               // List<StudentInfo> boys = studentList.stream().filter(s->s.getGender() && s.getHeight() >= 1.8).collect(Collectors.toList());
                UserInfo.invalid(),
                Collections.emptyList()
        );
    }
}
