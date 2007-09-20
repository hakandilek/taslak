<%@ include file="/common/taglibs.jsp"%>

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

<c:out value="${buttons}" escapeXml="false" />

<display:table name="products" class="table" requestURI="" id="productList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="editProduct.html" media="html"
        paramId="id" paramProperty="id" titleKey="product.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="product.id"/>
    <display:column property="name" sortable="true" titleKey="product.name"/>
    <display:column property="price" sortable="true" titleKey="product.price"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="productList.product"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="productList.products"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="productList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="productList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="productList.title"/>.pdf</display:setProperty>
</display:table>

<c:out value="${buttons}" escapeXml="false" />

<script type="text/javascript">
    highlightTableRows("productList");
</script>
