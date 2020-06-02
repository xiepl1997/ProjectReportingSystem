package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.Block;
import lyn.projectreportingsystem.service.impl.BlockService;
import lyn.projectreportingsystem.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class UploadController {

    @Autowired
    private ProjectService projectService = null;

    @Autowired
    private BlockService blockService = null;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file-multiple") MultipartFile file,
                         @RequestParam("projectid") String projectid){
        if(file.isEmpty()){
            return "请选择文件！";
        }
        String fileName = file.getOriginalFilename();
        //设置文件上传位置
        String filePath = "C:\\Users\\xiepl\\Desktop\\testfiles\\";
        File dest = new File(filePath + fileName);
        try{
            file.transferTo(dest);
            //更新项目
            projectService.updatefile(Integer.parseInt(projectid), filePath+fileName);
            //插入block
            Block block = blockService.getlasterblock(projectid);
            block.setFile(filePath+fileName);
            block.setTimestamp(new Date().getTime());
            block.previous_hash = block.hash;
            block.hash = block.calculateHash();
            block.mineBlock(2);
            blockService.insertblock(block);
            return "上传成功";
        }catch (IOException e){
            ;
        }
        return "上传失败！";
    }
}
