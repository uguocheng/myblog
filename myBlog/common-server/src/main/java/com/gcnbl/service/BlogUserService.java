package com.gcnbl.service;

import com.gcnbl.constant.BlogReqHeaderConst;
import com.gcnbl.constant.SessionKey;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BlogUserService {
    @Resource
    private BlogSessionService sessionService;

    @Resource
    private HttpServletRequest request;

    public Long getCurrentUid() {
        Object uid = sessionService.get(SessionKey.USER_TOKEN + getToken());
        return null == uid ? null : Long.valueOf(uid.toString());
    }

    public String getToken() {
        String token = request.getHeader(BlogReqHeaderConst.ERUPT_HEADER_TOKEN);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(BlogReqHeaderConst.URL_ERUPT_PARAM_TOKEN);
        }
        return token;
    }

}
