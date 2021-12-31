package com.ywbest.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * Spring RequestContextHolder를 활용한 유틸
 * Controller와 동일한 thread 내에서는
 * Controller-Service-Dao 전구간에서
 * HttpServletRequest에 보관해둔 정보를 사용 가능
 */
public class RequestScopeUtil {

    public static Object getAttribute(String name) {
        return (Object) RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    public static void setAttribute(String name, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_REQUEST);
    }

    public static void removeAttribute(String name) {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}