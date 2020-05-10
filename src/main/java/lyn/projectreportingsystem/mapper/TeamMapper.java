package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.Team;
import lyn.projectreportingsystem.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamMapper {

    /**
     * 由用户email获取该用户所在团队
     */
    @Select("select t.* from user u, team t, user_team ut where u.email = #{email} and u.email=ut.email and t.teamid=ut.teamid")
    List<Team> getTeamsByUserEmail(@Param("email") String email);

    /**
     * 由teamid获取team信息
     * @param teamid
     * @return
     */
    @Select("select * from team where teamid = #{teamid}")
    Team getTeamByTeamid(@Param("teamid") int teamid);

    /**
     * 由teamid获取团队成员数量
     * @param teamid
     * @return
     */
    @Select("select count(*) from user_team where teamid=#{teamid}")
    int getCountOfmembersByTeamid(@Param("teamid") int teamid);

    /**
     * 插入user_team表
     * @param email
     * @param teamid
     * @param islead
     * @return
     */
    @Insert("insert into user_team(email, teamid, islead) values(#{email}, #{teamid}, #{islead})")
    boolean insert_user_team(@Param("email") String email, @Param("teamid")String teamid, @Param("islead") int islead);

    /**
     * 插入team，成功插入后id反填到user
     * @return
     */
    @Insert("insert into team(teamname,teamremark,establishtime,leader) values(#{teamname},#{teamremark},#{establishtime},#{leader})")
    @Options(useGeneratedKeys = true, keyProperty = "teamid", keyColumn = "teamid")
    void insertteam(Team team);

}
