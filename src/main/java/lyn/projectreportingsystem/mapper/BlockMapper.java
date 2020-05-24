package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.Block;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    @Select("select * from block where projectid = #{projectid} order by timestamp desc")
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
    @Insert("insert into block(email,projectid,timestamp,projectname,starttime,endtime,money,type,tertiarydiscipline,projectremark,previous_hash,hash,nonce) values(#{email},#{projectid},#{timestamp},#{projectname},#{starttime},#{endtime},#{money},#{type},#{tertiarydiscipline},#{projectremark},#{previous_hash},#{hash},#{nonce})")
    boolean insertblock(Block block);
}
