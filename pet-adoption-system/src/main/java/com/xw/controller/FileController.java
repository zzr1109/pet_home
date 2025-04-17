package com.xw.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.xw.common.Constants;
import com.xw.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("file")
@Tag(name = "文件上传模块")
public class FileController {
    private static final String UPLOAD_PATH = System.getProperty("user.dir") + File.separator + "/files";

    @Value("${server.port}")
    private String port;


    @PostMapping("upload")
    @Operation(summary = "文件上传")
    public Result<?> upload(@RequestParam MultipartFile file) throws IOException {
        // 1 获取文件名
        String originalFilename = file.getOriginalFilename();
        // 2 获取文件类型
        String fileType = FileUtil.extName(originalFilename);
        // 3 定义一个文件的标识
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + fileType;
        // 4 开始上传
        File uploadFile = new File(UPLOAD_PATH + "/" + fileUUID);
        // 如果files文件夹不存在，则创建一个
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        // 上传文件到磁盘
        file.transferTo(uploadFile);
        // 5 返回给前端
        String url = "http://localhost:" + port + "/file/" + fileUUID;
        return new Result<>(url, Constants.SUCCESS);
    }
}
