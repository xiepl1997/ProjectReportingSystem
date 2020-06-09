package lyn.projectreportingsystem.service;

import lyn.projectreportingsystem.pojo.Block;

import java.util.List;

public interface IBlockService {

    List<Block> getblockbyprojectid(String projectid);

    boolean insertfirstblock(Block block);

    boolean insertblock(Block block);

    Block getlasterblock(String projectid);

    boolean deleteblockbytimestamp(String projectid, String timestamp);

}
