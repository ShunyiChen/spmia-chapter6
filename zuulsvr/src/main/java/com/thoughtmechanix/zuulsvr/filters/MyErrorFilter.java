package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyErrorFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(MyErrorFilter.class);
    private static final int  FILTER_ORDER=1;
    private static final boolean  SHOULD_FILTER=true;

    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setCharacterEncoding("UTF-8");
        logger.error("ERROR!!!",ctx.getThrowable());
        ctx.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.setResponseBody(ctx.getThrowable().getMessage());
        ctx.remove("throwable");
        ctx.put("hasError", true);

        logger.debug("ERROR!!!Adding the correlation id to the outbound headers. {}", filterUtils.getCorrelationId());
        ctx.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());

        logger.debug("ERROR!!!Completing outgoing request for {}.", ctx.getRequest().getRequestURI());

        return null;
    }
}
