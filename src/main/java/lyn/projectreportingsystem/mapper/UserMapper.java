package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
