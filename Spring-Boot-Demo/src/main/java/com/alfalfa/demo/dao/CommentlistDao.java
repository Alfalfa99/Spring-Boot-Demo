package com.alfalfa.demo.dao;

import com.alfalfa.demo.domain.Commentlist;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//基于Mybatis
@Repository
public interface CommentlistDao {

    /**
     * 增加一条评论
     * @param commentlist
     */
    @Insert("INSERT INTO commentlist(id, course_id, user_id, user_name, comment, parent_id,nullField,addtime)" +
            " VALUE(#default,#{cl.cid},#{cl.uid},#{cl.user_name},#{cl.comment},#{cl.parent_id}," +
            "#{cl.nullField},#{cl.addtime})")
    void addComment(@Param("cl") Commentlist commentlist);

    /**
     * 删除一条评论
     * @param id
     */
    @Delete("DELETE FROM commentlist WHERE id = #{id}")
    void delComment(@Param("id") Long id);

    /**
     * 找到课程下的所有主评论
     * @param cid
     * @return
     */
    @Select("SELECT * FROM commentlist WHERE course_id = #{cid} AND parent_id = null")
    List<Commentlist> findAllComByCid(@Param("cid") Long cid);

    /**
     * 找到主评论下的副评论
     * @param clid
     * @return
     */
    @Select("SELECT * FROM commentlist WHERE  parent_id = #{clid}")
    List<Commentlist> findParentCom(@Param("clid") Long clid);

    /**
     * 找到副评论的主评论
     * @param id
     * @return
     */
    @Select("SELECT * FROM commentlist WHERE id = #{id}")
    List<Commentlist> findComByid(@Param("id") Long id);

    /**
     * 修改某一条评论
     * @param cl
     */
    @Update("UPDATE commentlist(id, course_id, user_id, user_name, comment, parent_id,nullField,addtime)" +
            " SET(#default,#{cl.cid},#{cl.uid},#{cl.user_name},#{cl.comment},#{cl.parent_id}," +
            "#{cl.nullField},#{cl.addtime}) WHERE id = #{cl.id}")
    void updateComment(@Param("cl") Commentlist cl);
}
