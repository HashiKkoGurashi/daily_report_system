<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}" >
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" type="date" var="reportDate" />
<label for="${AttributeConst.REP_DATE.getValue()}">日付</label><br />
<input type="date" value="<fmt:formatDate value='${reportDate}' pattern='yyyy-MM-dd' />" name="${AttributeConst.REP_DATE.getValue()}" id="${AttributeConst.REP_DATE.getValue()}" />
<br /><br />

<label>氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="${AttributeConst.REP_TITLE.getValue()}">タイトル</label><br />
<input type="text" name="${AttributeConst.REP_TITLE.getValue()}" id="${AttributeConst.REP_TITLE.getValue()}" value="${report.title}" />
<br /><br />

<label for="${AttributeConst.REP_CONTENT.getValue()}">内容</label><br />
<textarea name="${AttributeConst.REP_CONTENT.getValue()}" id="${AttributeConst.REP_CONTENT.getValue()}" rows="10" cols="50">${report.content}</textarea>
<br /><br />

<fmt:parseDate value="${report.attendAt}" pattern="HH:mm:ss" type="time" var="attendAt" />
<label for="${AttributeConst.REP_ATTEND.getValue()}">出勤時間</label><br />
<input type="time" value="<fmt:formatDate value='${attendAt}' pattern='HH:mm:ss' />" step="1" name="${AttributeConst.REP_ATTEND.getValue()}" id="${AttributeConst.REP_ATTEND.getValue()}" />
<br /><br />

<fmt:parseDate value="${report.leaveAt}" pattern="HH:mm:ss" type="time" var="leaveAt" />
<label for="${AttributeConst.REP_LEAVE.getValue()}">退社時間</label><br />
<input type="time" value="<fmt:formatDate value='${leaveAt}' pattern='HH:mm:ss' />" step="1" name="${AttributeConst.REP_LEAVE.getValue()}" id="${AttributeConst.REP_LEAVE.getValue()}" />
<br /><br />

<input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />

<button type="submit">投稿</button>
