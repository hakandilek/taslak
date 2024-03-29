<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='productDetail.heading'/>"/>
</head>

<!--copy URL-->
<s:url id="copyUrl" namespace="/Product" action="copyProduct.do" includeParams="false">
    <s:param name="idToCopy">
        <s:property value="product.id"/>
    </s:param>
</s:url>

<s:form id="productForm" namespace="/Product" action="saveProduct" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="product.id"/>
    </li>
        <s:textfield key="product.name" required="false" cssClass="text medium" labelposition="left"/>
        <s:textfield key="product.price" required="false" cssClass="text medium" labelposition="left"/>

    <tr>
		<td class="buttonBar bottom" colspan="2">
        <c:if test="${empty product.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty product.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('Product')" theme="simple"/>
            <s:a href="%{copyUrl}"><fmt:message key="button.copy"/></s:a>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
        </td>
	</tr>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("productForm"));
</script>
