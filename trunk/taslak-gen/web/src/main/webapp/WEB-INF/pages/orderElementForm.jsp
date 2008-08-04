<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderElementDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='orderElementDetail.heading'/>"/>
</head>

<!--copy URL-->
<s:url id="copyUrl" namespace="/OrderElement" action="copyOrderElement.do" includeParams="false">
    <s:param name="idToCopy">
        <s:property value="orderElement.id"/>
    </s:param>
</s:url>

<s:form id="orderElementForm" namespace="/OrderElement" action="saveOrderElement" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="orderElement.id"/>
    </li>
        <s:textfield key="orderElement.quantity" required="false" cssClass="text medium" labelposition="left"/>

    <li class="buttonBar bottom">
        <c:if test="${empty orderElement.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty orderElement.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('OrderElement')" theme="simple"/>
            <s:a href="%{copyUrl}"><fmt:message key="button.copy"/></s:a>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("orderElementForm"));
</script>
