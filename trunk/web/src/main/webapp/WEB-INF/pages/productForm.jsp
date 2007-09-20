<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='productDetail.heading'/>"/>
</head>

<s:form id="productForm" action="saveProduct" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="product.id"/>
    </li>
    <s:textfield key="product.name" required="true" cssClass="text medium"/>
    <s:textfield key="product.price" required="false" cssClass="text medium"/>

    <li class="buttonBar bottom">
        <s:submit cssClass="button" method="save" key="button.save" theme="simple"/>
        <c:if test="${not empty product.id}">
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('Product')" theme="simple"/>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("productForm"));
</script>
