<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="orderDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='orderDetail.heading'/>"/>
</head>

<s:form id="orderForm" namespace="/Order" action="saveOrder" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="order.id"/>
        <s:hidden key="idToCopy"/>
    </li>

    <c:if test="${not empty order.id}">

        <!--order element list URL-->
        <s:url id="url" namespace="/OrderElement" action="editOrderElement.do" includeParams="none">
            <s:param name="orderId">
                <s:property value="order.id"/>
            </s:param>
        </s:url>

        <!--copy URL-->
        <s:url id="copyUrl" namespace="/Order" action="copyOrder.do" includeParams="none">
            <s:param name="idToCopy">
                <s:property value="order.id"/>
            </s:param>
        </s:url>
    
        <s:a href="%{url}"><fmt:message key="OrderElement.child.link"/></s:a>
    </c:if>
    <li class="pad">&nbsp;</li>

    <s:textfield key="order.name" required="true" cssClass="text medium" labelposition="left" />
    <s:textfield key="order.priceTotals" required="true" cssClass="text medium" labelposition="left" />
    <s:textfield key="order.createDate" required="true" cssClass="text" size="11" title="date" labelposition="left" />
    <li class="buttonBar bottom">
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
    </li>
</s:form>

<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar-setup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/lang/calendar-en.js'/>"></script>
<script type="text/javascript">
    Form.focusFirstElement($("orderForm"));
    Calendar.setup({inputField: "orderForm_order_createDate", ifFormat: "%d/%m/%Y", button: "order.createDateDatePicker"});
</script>
