package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
