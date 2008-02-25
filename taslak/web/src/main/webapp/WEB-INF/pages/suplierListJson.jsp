<%@ include file="/common/taglibs.jsp"%>
<%@page contentType="text/json; charset=UTF-8" %>
[
<s:iterator value="suppliers" status="iteratorStatus">
	["<s:property value="name"/>",<s:property value="id" />]<s:if test="!#iteratorStatus.last">,</s:if>
</s:iterator>
]
