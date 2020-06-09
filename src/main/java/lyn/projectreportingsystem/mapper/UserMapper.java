package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where email = #{email} and password = #{password}")
    User SelectUserByEmailPassword(@Param("email") String email, @Param("password") String password);

    @Select("select * from user where email = #{email}")
    User SelectUserByEmail(@Param("email") String email);

    @Insert("insert into user(email,name,password,phone,sex,school,college) values(#{email},#{name},#{password},#{phone},#{sex},#{school},#{college})")
    int InsertUser(User user);

    @Select("select u.* from user u ,user_team where user_team.teamid = #{teamid} and u.email=user_team.email")
    List<User> getMembersInSameTeamByTeamID(@Param("teamid") int teamid);

    /**
     * 选择出一个团队的负责人
     * @param teamid
     * @return
     */
    @Select("select email from user_team where teamid = #{teamid} and islead = 1")
    String selectLeader(@Param("teamid") int teamid);

    /**
     * 删除指定团队中的指定的人
     * @param email
     * @param teamid
     * @return
     */
    @Delete("delete from user_team where teamid = #{teamid} and email = #{email}")
    int deleteMember(@Param("email") String email, @Param("teamid") int teamid);

    /**
     * 更新用户信息
     * @param email
     * @param name
     * @param phone
     * @param sex
     * @param school
     * @param college
     * @return
     */
    @Update("update user set name=#{name},phone=#{phone},sex=#{sex},school=#{school},college=#{college} where email=#{email}")
    boolean updateuser(@Param("email")String email,
                       @Param("name")String name,
                       @Param("phone")String phone,
                       @Param("sex")String sex,
                       @Param("school")String school,
                       @Param("college")String college);
}
