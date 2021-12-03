package com.kon.ftp;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.ftp.session.FtpFileInfo;

import java.io.File;
import java.util.List;

/**
 * note
 *
 * @author com.kon, created on 2021/12/1T15:15.
 * @version 1.0.0-SNAPSHOT
 */
@MessagingGateway
public interface FtpGateway {

    /**
     * 目录下的文件属性
     * @param dir   文件目录
     * @return  文件属性集合
     */
    @Gateway(requestChannel = "lsChannel")
    List<FtpFileInfo> listFile(String dir);

    /**
     * 下载文件
     * @param remotePath 文件路径
     * @return 文件
     */
    @Gateway(requestChannel = "downloadChannel")
    File downloadFile(String remotePath);

    /**
     * 下载目录下所有文件
     * @param dir   文件目录
     * @return 所有文件
     */
    @Gateway(requestChannel = "downloadsChannel")
    List<File> downloadFiles(String dir);

    /**
     * 上传文件
     * @param file  文件
     */
    @Gateway(requestChannel = "uploadChannel")
    void uploadFile(File file);
}
