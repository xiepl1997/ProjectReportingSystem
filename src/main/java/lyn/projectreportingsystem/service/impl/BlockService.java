package lyn.projectreportingsystem.service.impl;

import lyn.projectreportingsystem.mapper.BlockMapper;
import lyn.projectreportingsystem.pojo.Block;
import lyn.projectreportingsystem.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService implements IBlockService {

    @Autowired
    private BlockMapper blockMapper = null;

    @Override
    public List<Block> getblockbyprojectid(String projectid) {
        return blockMapper.getblockbyprojectid(projectid);
    }

    @Override
    public boolean insertfirstblock(Block block) {
        return blockMapper.insertfirstblock(block);
    }

    @Override
    public boolean insertblock(Block block) {
        return blockMapper.insertblock(block);
    }

    @Override
    public Block getlasterblock(String projectid) {
        return blockMapper.getlasterblock(projectid);
    }

    @Override
    public boolean deleteblockbytimestamp(String projectid, String timestamp) {
        return blockMapper.deleteblockbytimestamp(projectid, timestamp);
    }
}
