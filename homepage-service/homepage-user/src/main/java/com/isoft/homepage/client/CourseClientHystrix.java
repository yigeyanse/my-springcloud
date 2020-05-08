package com.isoft.homepage.client;

import com.isoft.homepage.CourseInfo;
import com.isoft.homepage.CourseInfosRequest;
import com.isoft.homepage.CourseInfo;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * <h1>熔断降级</h1>
 * Created by Qinyi.
 */
@Component
public class CourseClientHystrix implements CourseClient {

    @Override
    public CourseInfo getCourseInfo(Long id) {
        return CourseInfo.invalid();
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {
        return Collections.emptyList();
    }
}
