<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.ac.kku.cs.wp.DaeKuen16.user.entity.User"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="custom" uri="kr.ac.kku.cs.wp.DaeKuen16.tools.tags.custom"%>

<div id="user-count" style="margin-bottom: 20px;">
    전체: <strong>${fn:length(requestScope.users)}</strong>
</div>

<div class="user-card-container" id="user-list">
    <c:forEach var="user" items="${users}" >
        <custom:userCard status="${user.status}" email="${user.email}" name="${user.name}" 
            roles="${user.userRoles[0].role.role},${user.userRoles[1].role.role}" id="${user.id}" />
    </c:forEach>
</div>