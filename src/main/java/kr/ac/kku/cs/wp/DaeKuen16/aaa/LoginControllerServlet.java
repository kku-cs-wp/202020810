package kr.ac.kku.cs.wp.DaeKuen16.aaa;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;
import kr.ac.kku.cs.wp.DaeKuen16.user.service.UserService;
import kr.ac.kku.cs.wp.DaeKuen16.user.service.UserServiceImple;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginControllerServlet extends HttpServlet {
    
    private static final Logger logger = LogManager.getLogger(LoginControllerServlet.class);
    
    // UserServiceImple 인스턴스 생성
    private UserService userService = new UserServiceImple();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String context = req.getServletContext().getContextPath();
        String uriStr = req.getRequestURI().replaceAll(context, "");

        log("in post: " + uriStr);

        logger.entry();
        
        if (uriStr.equals("/login")) { // 로그인 처리
            String id = req.getParameter("username");
            String password = req.getParameter("password");
            
            // DB에서 사용자 정보를 가져옴
            User user = userService.getUserById(id);  // DB에서 사용자 ID로 사용자 조회
            
            if (user == null) {  // 사용자가 존재하지 않으면
                req.setAttribute("error", "login_fail");
                req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
            } else {
                log(password);
                log(user.getPassword());
                if (!password.equals(user.getPassword())) {  // 비밀번호가 일치하지 않으면
                    req.setAttribute("error", "login_fail");
                    req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    Account ac = new Account();
                    ac.setId(id);
                    ac.setName(user.getName());
                    ac.setRoles(user.getUserRoles());  // 역할을 가져옴
                    ac.setEmail(user.getEmail());
                    session.setAttribute("user", ac);
                    resp.sendRedirect(context);  // 로그인 성공 시 메인 페이지로 리다이렉트
                }
            }
        } else if (uriStr.equals("/logout")) { // 로그아웃 처리
            HttpSession session = req.getSession();
            if (session != null) {
                session.invalidate();
            }
            resp.sendRedirect(context + "/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String context = req.getServletContext().getContextPath();
        String uriStr = req.getRequestURI().replaceAll(context, "");

        log("In service : " + uriStr);

        if (uriStr.equals("/login")) {
            req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
        }
    }
}
