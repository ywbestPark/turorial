package com.ywbest.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Spring RequestContextHolder를 활용하여
 * Controller-Service-Dao 전구간에서
 * HttpServletRequest의 Session에 보관해둔 정보를 사용할 수 있도록 해주는 유틸리티 입니다.
 * HttpServletRequest 의 Session에 보관되는 정보이기 때문에
 * 다른 WAS에서도 동일한 SESSION_ID만 가지고 있다면 동일한 attribute를 꺼내서 사용할 수 있습니다.
 */
public class SessionScopeUtil {

    public static Object getAttribute(String name) throws Exception {
        return (Object)RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static void setAttribute(String name, Object object) throws Exception {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    public static void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    public static String getSessionId() throws Exception  {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
}