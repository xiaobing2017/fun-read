package com.bing.funread.server.service.impl;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.dto.WeChatAccessTokenDto;
import com.bing.funread.common.dto.WeChatLoginCheckDto;
import com.bing.funread.common.exception.ServiceException;
import com.bing.funread.common.utils.DateUtil;
import com.bing.funread.common.utils.HttpUtil;
import com.bing.funread.common.utils.RandomUtil;
import com.bing.funread.request.ShareCodeRequest;
import com.bing.funread.response.ResultCode;
import com.bing.funread.response.ResultMessage;
import com.bing.funread.server.service.FileService;
import com.bing.funread.server.service.ShareService;
import com.bing.funread.server.service.WeChatService;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Map;

/**
 * Description:微信接口服务类
 * Author: zhangfusheng
 * Date: 2018/4/3 下午3:36
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);

    @Value("${weChat.app.id}")
    private String appId;

    @Value("${weChat.app.secret}")
    private String appSecret;

    @Value("${weChat.login.check.url}")
    private String checkUrl;

    @Value("${weChat.access.token.url}")
    private String tokenUrl;

    @Value("${weChat.app.code.url}")
    private String codeUrl;

    @Autowired
    private FileService fileService;

    @Autowired
    private ShareService shareService;

    /**
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     */
    private static WeChatAccessTokenDto accessTokenDto;

    private static long cacheTime;

    @Override
    public WeChatLoginCheckDto loginCheck(String code) {
        String url = String.format(checkUrl, appId, appSecret, code);
        WeChatLoginCheckDto checkResult = HttpUtil.httpsRequest(url, RequestMethod.GET.name(), null, WeChatLoginCheckDto.class);
        if (checkResult == null || StringUtils.isBlank(checkResult.getSession_key())) {
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
        return checkResult;
    }

    @Override
    public String getAccessToken() {
        // redis缓存 TODO
        synchronized (WeChatServiceImpl.class) {
            if (cacheTime <= DateUtil.getCurrentTime().getTime() || accessTokenDto == null || accessTokenDto.getAccessToken() == null) {
                accessTokenDto = requestAccessToken();
                cacheTime = DateUtil.getCurrentTime().getTime() + (accessTokenDto.getExpiresIn() - 60) * 1000;
            }
            return accessTokenDto.getAccessToken();
        }
    }

    private WeChatAccessTokenDto requestAccessToken() {
        String url = String.format(tokenUrl, appId, appSecret);
        WeChatAccessTokenDto accessTokenDto = HttpUtil.httpsRequest(url, RequestMethod.GET.name(), null, WeChatAccessTokenDto.class);
        if (accessTokenDto == null || StringUtils.isBlank(accessTokenDto.getAccessToken())) {
            throw new ServiceException(ResultCode.FAILED, ResultMessage.FAILED);
        }
        return accessTokenDto;
    }

    @Override
    public String getShareCode(ShareCodeRequest request) {
        RestTemplate rest = new RestTemplate();
        String accessToken = getAccessToken();
        String url = String.format(codeUrl, accessToken);
        Map<String, Object> param = Maps.newHashMap();
        param.put("scene", shareService.getUniqueId(request));
        param.put("page", request.getPage());
        param.put("width", 430);
        param.put("auto_color", false);
        Map<String,Object> line_color = Maps.newHashMap();
        line_color.put("r", 0);
        line_color.put("g", 0);
        line_color.put("b", 0);
        param.put("line_color", line_color);
        logger.info("调用生成微信永久小程序码接口参数：{}", param);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity requestEntity = new HttpEntity(param, headers);
        ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
        byte[] result = entity.getBody();
        logger.info("调用生成微信永久小程序码接口结果：{}", result);
        logger.info(Base64.encodeBase64String(result));

        String path = CommonConstant.FILE_DIR_SHARE + File.separator + RandomUtil.randomByDate() + ".jpg";
        fileService.saveFile(result, path);

        return path;
    }
}
