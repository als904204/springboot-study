package com.example.securityjwt.common.util;

import com.example.securityjwt.common.exception.ExceptionCode;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static void setErrorResponse(HttpServletResponse response, int status,
        ExceptionCode message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var errorMsg = String.format("""
            {
                "status" : %d,
                "message" : "%s"
            }
            """, status, message.toString());

        response.getWriter().write(errorMsg);
    }

}
