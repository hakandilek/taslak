<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="orderElementDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='orderElementDetail.heading'/>"/>
</head>

<s:form id="orderElementForm" action="saveOrderElement" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="orderElement.id"/>
    </li>
    <s:textfield required="true" cssClass="text medium" key="orderElement.quantity"/>
    <s:select name="orderElement.order.id" list="orderList" listKey="id" listValue="id"
              key="orderElement.order" headerKey="-12345678" headerValue="Select.choose"/>
    <s:select name="orderElement.product.id" list="productList" listKey="id" listValue="id"
              key="orderElement.product" headerKey="-12345678" headerValue="Select.choose"/>
    <li class="buttonBar bottom">
        <c:if test="${empty orderElement.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty orderElement.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                      onclick="return confirmDelete('OrderElement')" theme="simple"/>
            <s:submit cssClass="button" method="copy" action="copyOrder" key="button.copy" theme="simple"/>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("orderElementForm"));
</script>
