<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderElementDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='orderElementDetail.heading'/>"/>
</head>

<s:form id="orderElementForm" action="saveOrderElement" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="orderElement.id"/>
    </li>
    <s:textfield key="orderElement.quantity" required="true" cssClass="text medium"/>
    <%--<s:select name="orderElement.order.id" list="orderList" listKey="id" listValue="id"></s:select>--%>
    <%--<s:select name="orderElement.product.id" list="productList" listKey="id" listValue="id"></s:select>--%>

    <li class="buttonBar bottom">
        <s:submit cssClass="button" method="save" key="button.save" theme="simple"/>
        <c:if test="${not empty orderElement.id}">
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('OrderElement')" theme="simple"/>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("orderElementForm"));
</script>
