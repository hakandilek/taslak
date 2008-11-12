<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='orderDetail.heading'/>"/>
</head>

<!--copy URL-->
<s:url id="copyUrl" namespace="/Order" action="copyOrder.do" includeParams="false">
    <s:param name="idToCopy">
        <s:property value="order.id"/>
    </s:param>
</s:url>

<s:form id="orderForm" namespace="/Order" action="saveOrder" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="order.id"/>
    </li>
        <s:textfield key="order.name" required="false" cssClass="text medium" labelposition="left"/>
        <s:textfield key="order.priceTotals" required="false" cssClass="text medium" labelposition="left"/>
        <s:textfield key="order.createDate" required="false" cssClass="text medium" labelposition="left"/>

    <tr>
		<td class="buttonBar bottom" colspan="2">
        <c:if test="${empty order.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty order.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('Order')" theme="simple"/>
            <s:a href="%{copyUrl}"><fmt:message key="button.copy"/></s:a>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
        </td>
	</tr>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("orderForm"));
</script>
