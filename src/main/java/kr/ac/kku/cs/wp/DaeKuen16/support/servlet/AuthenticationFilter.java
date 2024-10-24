package kr.ac.kku.cs.wp.DaeKuen16.support.servlet;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.DaeKuen16.aaa.Account;
import kr.ac.kku.cs.wp.DaeKuen16.tools.message.MessageException;

@WebFilter("/user/*")  // /user 이하의 경로에 대해 필터 적용
public class AuthenticationFilter implements Filter {
	

    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 설정이 필요하다면 여기에 추가 가능
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession hs = req.getSession(false);

        // 로그인된 사용자 정보가 있는 경우
        Account user = (Account) hs.getAttribute("user");
        
        if(user != null) {
        	String context = req.getServletContext().getContextPath();
        	logger.info(user.getId() + "accessed " + req.getRequestURI().replace(context, ""));
        	chain.doFilter(request, response);
        } else {
        	throw new MessageException("login_required");
        }

    }
}