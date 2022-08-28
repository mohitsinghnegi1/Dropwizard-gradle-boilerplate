package com.example.helloworld;

import org.eclipse.jetty.util.StringUtil;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.UUID.randomUUID;

/**
 * Created by ali.nalawala on 5/15/14.
 */
public class RequestContextFilter implements Filter {

    public static final String REQUEST_ID = "reqId";
    public static final String TRANSACTION_ID = "txnId";
    public static final String URI = "uri";
    public static final List<String> SELLER_ID_HEADERS = newArrayList("X_SELLER_ID", "X-SELLER-ID");
    public static final List<String> CLIENT_ID_HEADERS = newArrayList("X_CLIENT_ID", "X-CLIENT-ID");
    public static final List<String> TRANSACTION_ID_HEADERS =
            newArrayList("X-TRANSACTION-ID", "X_TRANSACTION_ID", "X_RESTBUS_TRANSACTION_ID",
                    "X-REQUEST-ID");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String sellerId = getHeader(req, SELLER_ID_HEADERS).orElse(null);
        String transactionId = getHeader(req, TRANSACTION_ID_HEADERS).orElse("TXN-" + randomUUID());
        String clientId = getHeader(req, CLIENT_ID_HEADERS).orElse(null);

        String requestId = "REQ-" + randomUUID();
        RequestContext.setRequestId(requestId);
        RequestContext.setTransactionId(transactionId);
        RequestContext.setSellerId(sellerId);
        RequestContext.setClientId(clientId);

        MDC.put(REQUEST_ID, requestId);
        MDC.put(TRANSACTION_ID, transactionId);
        MDC.put(URI, ((HttpServletRequest) request).getRequestURI());
        try {
            chain.doFilter(request, response);
        } finally {
            RequestContext.clear();
            MDC.remove(REQUEST_ID);
            MDC.remove(TRANSACTION_ID);
            MDC.remove(URI);
        }
    }

    @Override
    public void destroy() {

    }

    private Optional<String> getHeader(HttpServletRequest req, List<String> headerNames) {
        return headerNames.stream()
                .filter(input -> StringUtil.isNotBlank(req.getHeader(input)))
                .findFirst()
                .map(input -> req.getHeader(input));
    }
}

