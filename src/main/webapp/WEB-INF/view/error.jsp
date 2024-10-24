<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.logging.log4j.LogManager, org.apache.logging.log4j.Logger" %>

<%! private static final Logger logger = LogManager.getLogger("error.jsp"); %>

<%
    logger.error(pageContext.getErrorData().getThrowable().getMessage());
    response.setHeader("Error-Message", pageContext.getErrorData().getThrowable().getMessage());
    // response.setStatus(200);  // 상태 코드가 200으로 설정된 부분은 주석 처리
%>

<h2 id="modal-title">Error Message</h2>
<p id="modal-message">${pageContext.errorData.throwable.message}</p>