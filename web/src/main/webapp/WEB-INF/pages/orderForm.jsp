<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="orderDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='orderDetail.heading'/>"/>
</head>

<s:form id="orderForm" action="saveOrder" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="order.id"/>
    </li>
    <s:textfield key="order.name" required="true" cssClass="text medium"/>
    <s:textfield key="order.priceTotals" required="true" cssClass="text medium"/>
    <s:textfield key="order.createDate" required="true" cssClass="text" size="11" title="date"/>

    <li class="buttonBar bottom">
        <c:if test="${empty order.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty order.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                      onclick="return confirmDelete('Order')" theme="simple"/>
            <s:submit cssClass="button" method="copy" action="copyOrder" key="button.copy" theme="simple"/>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar-setup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/lang/calendar-en.js'/>"></script>
<script type="text/javascript">
    Form.focusFirstElement($("orderForm"));
    Calendar.setup({inputField: "orderForm_order_createDate", ifFormat: "%d/%m/%Y", button: "order.createDateDatePicker"});
</script>
