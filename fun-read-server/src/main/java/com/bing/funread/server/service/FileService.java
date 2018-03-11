package com.bing.funread.server.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description:文件接口
 * Author: zhangfusheng
 * Date: 2018/3/11 下午4:26
 */
public interface FileService {

    /**
     * 上传文件
     * @param file 文件对象
     * @param saveDir 文件保存路径
     * @param filename 文件保存名称
     * @return
     */
    String upload(MultipartFile file, String saveDir, String filename);
}
