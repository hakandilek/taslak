<%@ include file="/common/taglibs.jsp"%>
<%@page contentType="text/json; charset=UTF-8" %>
[
<s:iterator value="orderElements" status="iteratorStatus">
	"orderElement": {
		"id" : <s:property value="id"/>,
		"quantity" : <s:property value="quantity" />, 
		"order.id" : <s:property value="order.id" />,
		"product.id" : <s:property value="product.id" />
	}<s:if test="!#iteratorStatus.last">,</s:if>
	
</s:iterator>
]
