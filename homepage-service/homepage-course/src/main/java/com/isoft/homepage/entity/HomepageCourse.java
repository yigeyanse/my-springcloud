package com.isoft.homepage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <h1>homepage_course 映射实体表定义</h1>
 * Created by Qinyi.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "homepage_course")
public class HomepageCourse {

    /** 数据表主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** 课程名称 */
    @Basic
    @Column(name = "course_name", nullable = false)
    private String courseName;

    /** 课程类型: 0(免费课), 1(实战课) */
    @Basic
    @Column(name = "course_type", nullable = false)
    private Integer courseType;

    /** 课程图标 */
    @Basic
    @Column(name = "course_icon", nullable = false)
    private String courseIcon;

    /** 课程介绍 */
    @Basic
    @Column(name = "course_intro", nullable = false)
    private String courseIntro;

    /** 创建时间 */
    @Basic
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /** 更新时间 */
    @Basic
    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public HomepageCourse(String courseName, Integer courseType,
                          String courseIcon, String courseIntro) {

        this.courseName = courseName;
        this.courseType = courseType;
        this.courseIcon = courseIcon;
        this.courseIntro = courseIntro;
    }

    /**
     * <h2>返回一个无效的课程</h2>
     * */
    public static HomepageCourse invalid() {

        HomepageCourse invalid = new HomepageCourse("", 0, "", "");
        invalid.setId(-1L);
        return invalid;
    }
}
