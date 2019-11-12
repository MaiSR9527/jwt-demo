package com.msr.controller;

import com.msr.dto.Result;
import com.msr.dto.StatusCode;
import com.msr.entity.User;
import com.msr.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: maishuren
 * @Date: 2019/11/12 10:46
 */
@RestController
@CrossOrigin
public class UserController {

    /**
     * 在启动类配置了bean
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     *
     * @return 返回页面需要的信息
     */
    @GetMapping("/login")
    public Result login(HttpServletResponse response) {
        System.out.println(jwtUtil.getKey());
        System.out.println(jwtUtil.getTtl());
        //模拟接受到的参数
        User user = new User("132******", "msr", "mima", "admin");
        //模拟查询数据库 user = userService.login(user.getMobile(), user.getPassword());
        if (ObjectUtils.isEmpty(user)) {
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }

        String token = jwtUtil.createJwt(user.getMobile(), user.getName(), user.getRole());

        Map<String, Object> map = new HashMap<>();

        // 当请求头是Authorization Cookies之类的敏感信息，zuul会拦截，那就需要添加过滤器 WebFilter
        // 在配置文件中添加sensitive-headers:
        map.put("Authorization",token);
        map.put("user", user);

        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    /**
     * 模拟一个需要验证的操作
     */
    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        //获取请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String element = headerNames.nextElement();
            String header = request.getHeader(element);
            System.out.println(element+":"+header);
        }
        String token = request.getHeader("x-token");
        Claims claims = jwtUtil.parseJwt(token);

        return "role: "+claims.get("role")+"    mobile: "+claims.getSubject()
                +"   exp: "+claims.getExpiration()+"   createTime: "+claims.getIssuedAt();
    }


}
