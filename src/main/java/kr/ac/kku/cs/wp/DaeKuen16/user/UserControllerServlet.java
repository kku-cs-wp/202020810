package kr.ac.kku.cs.wp.DaeKuen16.user;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kku.cs.wp.DaeKuen16.tools.json.ReflectionUtil;
import kr.ac.kku.cs.wp.DaeKuen16.tools.message.MessageException;
import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;
import kr.ac.kku.cs.wp.DaeKuen16.user.service.UserService;
import kr.ac.kku.cs.wp.DaeKuen16.user.service.UserServiceImple;

@WebServlet("/user/userlist")
public class UserControllerServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(UserControllerServlet.class);
    private UserService userService = new UserServiceImple();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 서블릿 초기화 시 호출되는 메서드
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("doPost() method called");

        User paramUser = null;
        String contentType = req.getContentType();

        // 요청의 Content-Type이 JSON일 때
        if (contentType != null && contentType.equals("application/json")) {
            logger.trace("Handling JSON request");

            StringBuilder jsonBuffer = new StringBuilder();
            String line;
            BufferedReader reader = req.getReader();

            // JSON 데이터를 읽음
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }

            // 읽은 JSON 데이터를 JSONObject로 변환
            JSONObject jsonParams = new JSONObject(jsonBuffer.toString());

            try {
                // ReflectionUtil을 사용하여 User 객체를 생성
                paramUser = ReflectionUtil.createObjectFromJson(User.class, jsonParams);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MessageException(e.getMessage());
            }

            // 파라미터로 전달된 User 객체를 바탕으로 사용자 목록 조회
            List<User> users = userService.getUsers(paramUser);
            req.setAttribute("users", users);

            // JSON 요청에 대한 응답으로 사용자 목록을 card 형태로 반환
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/user/userCards.jsp");
            rd.forward(req, resp);

        } else {
            // JSON 요청이 아닌 경우 기본 사용자 목록 조회 및 렌더링
            List<User> users = userService.getUsers(paramUser); // 모든 사용자 조회
            req.setAttribute("users", users);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/user/userList.jsp");
            rd.forward(req, resp);
        }
    }
}