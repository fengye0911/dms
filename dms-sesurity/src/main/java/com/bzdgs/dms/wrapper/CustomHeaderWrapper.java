package com.bzdgs.dms.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @author ch
 * @ClassName: CustomHeaderWrapper
 * @Description: 自定义请求头包装器
 * @date 2018-08-06
 * @since 1.7
 */
public class CustomHeaderWrapper extends HttpServletRequestWrapper {

    private final Set<String> names = new HashSet<>();

    private final Map<String, String> headers = new HashMap<>();

    public CustomHeaderWrapper(HttpServletRequest request) {
        super(request);
        Enumeration<String> e = super.getHeaderNames();
        while (e.hasMoreElements()) {
            names.add(e.nextElement());
        }
    }

    public CustomHeaderWrapper(HttpServletRequest request, Map<String, String> headers) {
        this(request);
        if (headers != null && !headers.isEmpty()) {
            this.headers.putAll(headers);
            this.names.addAll(headers.keySet());
        }
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
        names.add(name);
    }

    @Override
    public String getHeader(String name) {
        if (headers.containsKey(name)) {
            return headers.get(name);
        } else {
            return super.getHeader(name);
        }
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(names);
    }

}
