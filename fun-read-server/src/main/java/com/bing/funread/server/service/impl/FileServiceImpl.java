package com.bing.funread.server.service.impl;

import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description:文件接口服务类
 * Author: zhangfusheng
 * Date: 2018/3/11 下午4:27
 */
@Service
public class FileServiceImpl implements FileService {

    public static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${web.upload-path}")
    private String FILE_BASE_DIR;

    @Override
    public String upload(MultipartFile file, String saveDir, String filename) {
        try {
            String sourceName = file.getOriginalFilename();
            String fileSuffix = sourceName.substring(sourceName.lastIndexOf("."), sourceName.length());
            String savePath = saveDir + filename + fileSuffix;
            File saveFile = new File(FILE_BASE_DIR + savePath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
            return savePath;
        } catch (IOException e) {
            logger.error("文件上传失败，{}", e);
            throw new ServiceException(ResultCode.FILE_UPLOAD_FAILURE, ResultMessage.FILE_UPLOAD_FAILURE);
        }
    }

    public void saveFile(byte[] bytes, String path) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new ByteArrayInputStream(bytes);
            File file = new File(FILE_BASE_DIR + path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
