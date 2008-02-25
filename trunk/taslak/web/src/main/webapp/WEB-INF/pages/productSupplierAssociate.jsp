<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="productSupplierDetail.title" /></title>
	<meta name="heading"
		content="<fmt:message key='productSupplierDetail.heading'/>" />
</head>

<!--product JSON URL-->
<s:url id="productJsonURL" namespace="/Product"
	action="copyProduct.do?ajax=true" includeParams="all">
	<s:param name="product">
		<s:property value="product.id" />
	</s:param>
</s:url>

<!--supplier JSON URL-->
<s:url id="supplierJsonURL" namespace="/Supplier"
	action="copySupplier.do?ajax=true" includeParams="all">
	<s:param name="supplier">
		<s:property value="supplier.id" />
	</s:param>
</s:url>

<s:form id="productSupplierForm" namespace="/ProductSupplier"
	action="associate" method="post" validate="true">

	<s:autocompleter theme="ajax" name="test"  list="{'apple','banana','grape','pear'}" autoComplete="false"/>
	
	<!--
	<s:autocompleter key="product.product" name="product.id"
		href="%{productJsonURL}" indicator="productIndicator" theme="ajax"
		loadOnTextChange="true" autoComplete="true" searchType="substring"
		forceValidOption="true" loadMinimumCount="1" />
	<img id="productIndicator"
		src="${pageContext.request.contextPath}/images/indicator.gif"
		alt="<fmt:message key='messages.loading'/>" style="display: none" />
	<s:autocompleter key="supplier.supplier" name="supplier.id"
		href="%{supplierJsonURL}" indicator="supplierIndicator" theme="ajax"
		loadOnTextChange="true" autoComplete="true" searchType="substring"
		forceValidOption="true" loadMinimumCount="1" />
	<img id="supplierIndicator"
		src="${pageContext.request.contextPath}/images/indicator.gif"
		alt="<fmt:message key='messages.loading'/>" style="display: none" />
	
	<li class="buttonBar bottom">
		<c:if test="${empty product.id}">
			<s:submit cssClass="button" method="save" key="button.add"
				theme="simple" />
		</c:if> 
		<c:if test="${not empty product.id}">
			<s:submit cssClass="button" method="save" key="button.update"
				theme="simple" />
			<s:submit cssClass="button" method="delete" key="button.delete"
				onclick="return confirmDelete('Product')" theme="simple" />
			<s:a href="%{copyUrl}">
				<fmt:message key="button.copy" />
			</s:a>
		</c:if> 
		<s:submit cssClass="button" method="cancel" key="button.cancel"
			theme="simple" />
	</li>
	-->
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("productSupplierForm"));
</script>
