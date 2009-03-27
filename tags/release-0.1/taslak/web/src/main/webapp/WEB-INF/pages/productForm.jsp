<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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
    <s:textfield key="product.name" required="true" cssClass="text medium" labelposition="left"/>
    <ida:money key="product.price" required="false" cssClass="text medium" labelposition="left"/>
    <s:select name="productTypeIds" value="product.productType.value" listKey="value" key="product.productType"
              list="productTypes" labelposition="left"/>

    <li class="buttonBar bottom">
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
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("productForm"));
</script>
