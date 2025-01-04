package com.logging.mdc.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class MDCFilter implements Filter {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    private static final String CORRELATION_ID_KEY = "correlationId";

    @Override
    public void doFilter(ServletRequest request,
        ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 1) 헤더에서 Correlation ID 가져오기
        String correlationId = httpRequest.getHeader(CORRELATION_ID_HEADER);

        // 2) 없으면 새로 생성
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        // 3) MDC에 저장 -> 로그 패턴에서 %X{correlationId} 사용 가능
        MDC.put(CORRELATION_ID_KEY, correlationId);

        try {
            // 요청 처리
            chain.doFilter(request, response);
        } finally {
            // 스레드풀 오염 방지 위해 반드시 clear()
            MDC.clear();
        }
    }
}
