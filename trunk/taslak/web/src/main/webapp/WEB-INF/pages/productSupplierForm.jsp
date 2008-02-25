<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="productSupplierDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='productSupplierDetail.heading'/>"/>
</head>

<!--copy URL-->
<s:url id="copyUrl" namespace="/ProductSupplierSupplier" action="copyProductSupplierSupplier.do" includeParams="false">
    <s:param name="idToCopy">
        <s:property value="productSupplier.id"/>
    </s:param>
</s:url>

<!--product JSON URL-->
<s:url id="productJsonURL" namespace="/Product"
	action="ajaxList.do?ajax=true" includeParams="all">
	<s:param name="product">
		<s:property value="product.id" />
	</s:param>
</s:url>

<!--supplier JSON URL-->
<s:url id="supplierJsonURL" namespace="/Supplier"
	action="ajaxList.do?ajax=true" includeParams="all">
	<s:param name="supplier">
		<s:property value="supplier.id" />
	</s:param>
</s:url>

<s:form id="productSupplierForm" namespace="/ProductSupplier" action="saveProductSupplier" method="post" validate="true">
    <li style="display: none">
        <s:hidden key="productSupplier.id"/>
    </li>
    <li>
	<s:autocompleter key="product.product" name="product.id"
		cssStyle="width: 150px;" href="%{productJsonURL}"
		indicator="productIndicator" theme="simple" loadOnTextChange="true"
		autoComplete="true" searchType="substring" forceValidOption="true"
		loadMinimumCount="2" />
	<img id="productIndicator"
		src="${pageContext.request.contextPath}/images/indicator.gif"
		alt="<fmt:message key='messages.loading'/>" style="display: none" />
    </li>

    <li>
	<s:autocompleter key="supplier.supplier" name="supplier.id"
		cssStyle="width: 150px;" href="%{supplierJsonURL}"
		indicator="supplierIndicator" theme="simple" loadOnTextChange="true"
		autoComplete="true" searchType="substring" forceValidOption="true"
		loadMinimumCount="2" />
	<img id="supplierIndicator"
		src="${pageContext.request.contextPath}/images/indicator.gif"
		alt="<fmt:message key='messages.loading'/>" style="display: none" />
    </li>

    <li class="buttonBar bottom">
        <c:if test="${empty productSupplier.id}">
            <s:submit cssClass="button" method="save" key="button.add" theme="simple"/>
        </c:if>
        <c:if test="${not empty productSupplier.id}">
            <s:submit cssClass="button" method="save" key="button.update" theme="simple"/>
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('ProductSupplier')" theme="simple"/>
            <s:a href="%{copyUrl}"><fmt:message key="button.copy"/></s:a>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("productSupplierForm"));
</script>
