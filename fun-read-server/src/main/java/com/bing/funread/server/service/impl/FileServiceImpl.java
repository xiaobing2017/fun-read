package com.bing.funread.server.service.impl;

import com.bing.funread.server.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Description:文件接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/11 下午4:27
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.file.base.dir}")
    private static String FILE_BASE_DIR;

    @Override
    public String upload(MultipartFile file, String saveDir, String filename) {
        try {
            File saveFile = new File(FILE_BASE_DIR + saveDir + filename);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
            return saveFile.getParent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
