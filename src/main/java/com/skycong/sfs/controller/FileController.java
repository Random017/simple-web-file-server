package com.skycong.sfs.controller;

import com.skycong.sfs.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ruanmingcong 2019.12.3 11:35
 */
@RestController
@RequestMapping("v0/api/file")
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 上传单个文件
     */
    @PostMapping("upload")
    Object updateOneFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null || file.getSize() == 0) {
            return "error file";
        }
        return fileService.updateOneFile(file);
    }

    @PostMapping("deleteOne")
    Object deleteOne(Integer fid) {
        if (fid == null) return "error fid";
        return fileService.deleteOne(fid);
    }

    @GetMapping("findByCondition")
    Object findByCondition(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        return fileService.findByCondition(page);
    }
}
