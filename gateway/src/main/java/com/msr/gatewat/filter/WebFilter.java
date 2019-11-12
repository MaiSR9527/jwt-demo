package com.msr.gatewat.filter;

import com.msr.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description:
 * @Author: maishuren
 * @Date: 2019/11/12 11:39
 */
@Component
public class WebFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
//        //添加过滤
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = currentContext.getRequest();
//        String header = request.getHeader("Authorization");
//        if (!StringUtils.isEmpty(header)) {
//            currentContext.addZuulRequestHeader("Authorization", header);
//            Map<String, String> zuulRequestHeaders = currentContext.getZuulRequestHeaders();
//            for (String s : zuulRequestHeaders.keySet()) {
//
//                System.out.println(s+":"+zuulRequestHeaders.get(s));
//            }
//        }
        return null;
    }
}
