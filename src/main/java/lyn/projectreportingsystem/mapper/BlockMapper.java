package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.Block;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlockMapper {

    /**
     * 获取区块通过项目id
     * @param projectid
     * @return
     */
    @Select("select * from block where projectid = #{projectid} order by timestamp")
    List<Block> getblockbyprojectid(@Param("projectid") String projectid);

    /**
     * 插入第一个block
     * @param block
     * @return
     */
    @Insert("insert into block(email,projectid,timestamp,projectname,type,projectremark,previous_hash,hash,nonce) values(#{email},#{projectid},#{timestamp},#{projectname},#{type},#{projectremark},#{previous_hash},#{hash},#{nonce})")
    boolean insertfirstblock(Block block);

    /**
     * 插入block
     * @param block
     * @return
     */
    @Insert("insert into block(email,projectid,timestamp,projectname,starttime,endtime,money,type,tertiarydiscipline,projectremark,file,previous_hash,hash,nonce) values(#{email},#{projectid},#{timestamp},#{projectname},#{starttime},#{endtime},#{money},#{type},#{tertiarydiscipline},#{projectremark},#{file},#{previous_hash},#{hash},#{nonce})")
    boolean insertblock(Block block);

    /**
     * 获取项目最新的提交
     * @param projectid
     * @return
     */
    @Select("select * from block where projectid = #{projectid} order by timestamp desc limit 1")
    Block getlasterblock(@Param("projectid") String projectid);

    @Delete("delete from block where projectid = #{projectid} and timestamp > #{timestamp}")
    boolean deleteblockbytimestamp(@Param("projectid")String projectid, @Param("timestamp")String timestamp);

}
