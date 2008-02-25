<%@ include file="/common/taglibs.jsp"%>
<%@page contentType="text/json; charset=UTF-8" %>
[
<s:iterator value="orders" status="iteratorStatus">
	["<s:property value="name"/>",<s:property value="id" />]<s:if test="!#iteratorStatus.last">,</s:if>
</s:iterator>
]
