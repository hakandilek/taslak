<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="productList.title"/></title>
    <meta name="heading" content="<fmt:message key='productList.heading'/>"/>
    <meta name="menu" content="ProductMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
           onclick="location.href='<c:url value="/editProduct.html"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteButtons">
	<input id="products_button_" name="method:deleteMass" 
		   value="<fmt:message key="button.delete"/>" 
		   onclick="return confirmDelete("Product")" type="submit" />
	
	<input type="checkbox" name="allbox" 
		   onclick="checkAll(document.getElementById("productsList"), "deleteId")" />
</c:set>

<s:form id="productsForm" action="products">
    <s:textfield key="productSearch.name" cssClass="text medium"/>
    <s:textfield key="productSearch.fromPrice" cssClass="text medium"/>
    <s:textfield key="productSearch.toPrice" cssClass="text medium"/>
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="productsList" action="products">
    <display:table name="products" class="table" requestURI="" id="productList" export="true" pagesize="25"
                   decorator="org.xmdl.taslak.webapp.decorator.BeanDecorator">
        <display:column property="id" sortable="true" href="editProduct.html" media="html"
                        paramId="id" paramProperty="id" titleKey="product.id"/>
        <display:column property="name" sortable="true" titleKey="product.name"/>
        <display:column property="price" sortable="true" titleKey="product.price"/>
        <display:column titleKey="button.copy" media="html">
            <a href="/copyProduct.html?idToCopy=<c:out value="${productList.id}"/>"><img src="/images/common/save.gif"></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteButtons}"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="productList.product"/>
        </display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="productList.products"/>
        </display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="productList.title"/>.xls
        </display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="productList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="productList.title"/>.pdf</display:setProperty>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

    <!--<script type="text/javascript">-->
    <!--highlightTableRows("productList");-->
    <!--</script>-->

</s:form>