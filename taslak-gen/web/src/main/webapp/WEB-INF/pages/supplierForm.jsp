<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="supplierDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='supplierDetail.heading'/>"/>
</head>

<!--copy URL-->
<s:url id="copyUrl" namespace="/Supplier" action="copySupplier.do" includeParams="false">
    <s:param name="idToCopy">
        <s:property value="supplier.id"/>
    </s:param>
</s:url>

<s:form id="supplierForm" namespace="/Supplier" action="saveSupplier" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="supplier.id"/>
    </li>
        <s:textfield key="supplier.name" required="false" cssClass="text medium" labelposition="left"/>

    <li class="buttonBar bottom">
        <c:if test="${empty supplier.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty supplier.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('Supplier')" theme="simple"/>
            <s:a href="%{copyUrl}"><fmt:message key="button.copy"/></s:a>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("supplierForm"));
</script>
