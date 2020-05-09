package lyn.projectreportingsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProjectMapper {

    /**
     * 由teamid获取该团队的项目数量
     * @param teamid
     * @return
     */
    @Select("select count(*) from team_project where teamid=#{teamid}")
    int getProjectCountByTeamid(@Param("teamid") int teamid);
}
