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
		<tr>
	   		<td class="tdLabel" colspan="2"><label class="desc"><fmt:message key="supplier.contact"/></label></td>
		</tr>
        <s:textfield key="supplier.contact.fullname" required="false" cssClass="text medium" labelposition="left"/>
        <s:textfield key="supplier.contact.phone" required="false" cssClass="text medium" labelposition="left"/>

    <tr>
		<td class="buttonBar bottom" colspan="2">
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
        </td>
	</tr>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("supplierForm"));
</script>
