package com.kon.controller;

import com.kon.ftp.FtpGateway;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ftp.session.FtpFileInfo;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * note
 *
 * @author com.kon, created on 2021/12/1T15:24.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
public class FtpController {

    @Autowired
    private FtpGateway ftpGateway;

    @Autowired
    FtpRemoteFileTemplate ftpRemoteFileTemplate;

    @GetMapping("/listFile/{dir}")
    List<FtpFileInfo> listFile(@PathVariable String dir) {
        return ftpGateway.listFile(dir);
    }

    @GetMapping("/download")
    File downloadFiles(@RequestParam String remotePath) {
//        String suffix = remotePath.substring(remotePath.lastIndexOf("."));
//        File download = new File(FileUtils.getTempDirectory(), UUID.randomUUID().toString() + suffix);
//        boolean b = ftpRemoteFileTemplate.get(remotePath, stream -> FileUtils.copyToFile(stream, download));
//        System.out.println("download ---->" + b);
//        return download;

        return ftpGateway.downloadFile(remotePath);
    }

    @PostMapping("/uploadFile")
    void uploadFile(@ApiParam("file") MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        originalFilename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        File storageFile = new File(FileUtils.getTempDirectory(), originalFilename);
        // 存储文件
        try {
            multipartFile.transferTo(storageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ftpGateway.uploadFile(storageFile);
    }
}
