<%@ include file="/common/taglibs.jsp"%>
[
<s:iterator value="products" status="iteratorStatus">
	["<s:property value="name"/>",<s:property value="id" />]<s:if test="!#iteratorStatus.last">,</s:if>
</s:iterator>
]
