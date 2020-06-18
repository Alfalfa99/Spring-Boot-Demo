package com.alfalfa.demo.dao;

import com.alfalfa.demo.domain.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {

    /**
     * 新增课程
     * @param course
     */
    @Insert("INSERT INTO course(id,course_name,course_term,teacher_name,comment_count,popcount,detail,addtime,nullField)" +
            "VALUES(#default,#{course.course_name},#{course.course_term},#{course.teacher_name},#default," +
            "#default,#{course.detail},#{course.addtime},#{course.nullField})")
    void addCourse(Course course);

    /**
     * 通过课程id删除课程
     * @param id
     */
    @Delete("DELETE FROM course WHERE id = #{id}")
    void delCourse(Long id);

    /**
     * 查询所有课程信息
     * @return
     */
    @Select("SELECT * FROM course")
    List<Course> findAllCourse();

    /**
     * 通过课程名或者课程id查询
     * @param cname
     * @param cid
     * @return
     */
    @Select("SELECT * FROM course WHERE course_name LIKE '%${cname}%' OR id = #{cid}")
    List<Course> findCourseBy(@Param("cname") String cname,@Param("cid") Long cid);

    /**
     * 通过课程id查找
     * @param cid
     * @return
     */
    @Select("SELECT * FROM course WHERE id = #{cid}")
    Course findCourseByid(@Param("cid") Long cid);

    /**
     * 通过用户id查询用户拥有的所有课程
     * @param id
     * @return
     */
    @Select("SELECT c.* FROM course c, course_user cs Where cs.user_id = #{id} c.id = cs.course_id")
    List<Course> findUserCourse(@Param("id") Long id);

    /**
     * 更新课程详情
     * @param c
     */
    @Update("UPDATE course SET course_name = #{c.course_name},course_term = #{c.course_term},teacher_name = #{c.teacher_name}," +
            "detail = #{c.detail} WHERE id = #{c.id}")
    void updateCourseDetail(@Param("c") Course c);

    @Update("UPDATE course SET popcount = #{popcount} WHERE id = #{cid}")
    void updateCoursePop(@Param("popcount") Long popcount, @Param("cid") Long cid);
}
