<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/json;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
[
<s:iterator value="suppliers" status="iteratorStatus">
	["<s:property value="name"/>",<s:property value="id" />]<s:if test="!#iteratorStatus.last">,</s:if>
</s:iterator>
]
