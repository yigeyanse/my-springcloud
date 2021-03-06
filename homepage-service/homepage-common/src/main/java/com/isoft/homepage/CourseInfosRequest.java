package com.isoft.homepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h1>课程信息请求对象定义</h1>
 * Created by yigeyanse.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfosRequest {

    private List<Long> ids;

}
