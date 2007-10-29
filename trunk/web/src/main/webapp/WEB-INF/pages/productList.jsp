<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="productList.title"/></title>
    <meta name="heading" content="<fmt:message key='productList.heading'/>"/>
    <meta name="menu" content="ProductMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
           onclick="location.href='<c:url value="/Product/editProduct.html"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteChecks">
	<input type="checkbox" name="allbox" 
		   onclick="checkAll(document.getElementById('productsList'), 'deleteId')" />
</c:set>

<s:form id="productsForm" namespace="/Product" action="products">
    <s:textfield key="productSearch.name" cssClass="text medium" labelposition="left"/>
    <s:textfield key="productSearch.fromPrice" cssClass="text medium" labelposition="left"/>
    <s:textfield key="productSearch.toPrice" cssClass="text medium" labelposition="left"/>
    <s:select name="productTypeIds" value="productSearch.productType.value" listKey="value" key="product.productType"
              list="productTypes" labelposition="left" headerKey="<%=""+Integer.MIN_VALUE%>" headerValue="*"/>
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="productsList" namespace="/Product" action="products">
    <display:table name="products" class="table" requestURI="" id="productList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="product.id"/>
        <display:column property="name" sortable="true" titleKey="product.name"/>
        <display:column property="price" sortable="true" titleKey="product.price"/>
        <display:column property="productType" sortable="true" titleKey="product.productType"/>

        <display:column media="html" titleKey="List.Edit" >
            <a href="/Product/editProduct.html?id=<c:out value="${productList.id}"/>"><ida:base-url context="icon" path="edit.gif"/></a>
        </display:column>
        <display:column media="html" titleKey="button.copy">
            <a href="/Product/copyProduct.html?idToCopy=<c:out value="${productList.id}"/>"><ida:base-url context="icon" path="save.gif"/></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteChecks}"/>

		<display:footer>
			<tr class="footer">
                <!-- change the "colspan" value below according to the number of columns -->
				<td colspan="5">&nbsp;</td>
				<td>
					<input id="productDeleteButton" name="method:deleteMass" 
						   value="<fmt:message key="button.delete"/>" 
						   onclick="return confirmDelete('Product')" type="submit" />
				</td>
			</tr>
		</display:footer>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="productList.product"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="productList.products"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="productList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="productList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="productList.title"/>.pdf</display:setProperty>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

    <!--<script type="text/javascript">-->
    <!--highlightTableRows("productList");-->
    <!--</script>-->

</s:form>