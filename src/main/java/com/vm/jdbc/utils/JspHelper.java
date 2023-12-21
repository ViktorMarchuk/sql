package com.vm.jdbc.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    private final static String JSP_PATH = "jsp/%s.jsp";

    public String getPath(String jsp) {
        return JSP_PATH.formatted(jsp);
    }
}
