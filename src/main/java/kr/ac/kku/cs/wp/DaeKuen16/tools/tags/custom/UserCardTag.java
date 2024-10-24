package kr.ac.kku.cs.wp.DaeKuen16.tools.tags.custom;

import java.io.IOException;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class UserCardTag extends SimpleTagSupport {

    // attribute
    private String name = null;
    private String email = null;
    private String id = null;
    private String roles = null;
    private String status = null;

    // Getter, Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();

        // 사용자 정보를 카드 형식으로 출력하는 HTML 문자열 생성
        String userCard = "<div class=\"user-card\" data-name=\"홍길동\" data-email=\"hong@example.com\" data-role=\"관리자, 개발자\" data-id=\"1001\">\n"
                + " <img src=\"https://via.placeholder.com/150\" alt=\"홍길동 사진\">\n"
                + " <div class=\"user-info\">\n"
                + " <h3>" + name + "</h3>\n"
                + " <p><strong>이메일:</strong> " + email + "</p>\n"
                + " <p><strong>역할:</strong> " + roles + "</p>\n"
                + " <p><strong>사번:</strong> " + id + "</p>\n"
                + " <p><strong>상태:</strong> " + status + "</p>\n"
                + " <button onclick=\"alert('" + name + "의 상세 정보')\">상세 보기</button>\n"
                + " </div>\n"
                + "</div>\n";

        // JSP 페이지에 HTML 출력
        getJspContext().getOut().print(userCard);
    }
}
