package com.bing.funread.server.service.impl;

import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.FileService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private String random() {
        return DateUtil.format(DateUtil.getCurrentTime(), "yyMMddHHmmssSSS") +
                RandomStringUtils.randomNumeric(5);
    }
}
