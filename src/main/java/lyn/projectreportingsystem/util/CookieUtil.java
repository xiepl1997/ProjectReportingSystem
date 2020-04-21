package lyn.projectreportingsystem.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param key
     * @param value
     * @param expiry
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int expiry){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }

    /**
     * 获取所有cookie
     * @param request
     * @return
     */
    public static Map<String, String> getCookies(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                map.put(cookies[i].getName(), cookies[i].getValue());
            }
        }
        return map;
    }

    /**
     * 清空所有的cookie
     * @param request
     * @param response
     */
    public static void clear(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> map = getCookies(request);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> me = iter.next();
            Cookie cookie = new Cookie(me.getKey(), "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
}
