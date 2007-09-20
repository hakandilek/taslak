<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderElementList.title"/></title>
    <meta name="heading" content="<fmt:message key='orderElementList.heading'/>"/>
    <meta name="menu" content="OrderElementMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
        onclick="location.href='<c:url value="/editOrderElement.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false" />

<display:table name="orderElements" class="table" requestURI="" id="orderElementList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="editOrderElement.html" media="html"
        paramId="id" paramProperty="id" titleKey="orderElement.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="orderElement.id"/>
    <display:column property="quantity" sortable="true" titleKey="orderElement.quantity"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="orderElementList.orderElement"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="orderElementList.orderElements"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="orderElementList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="orderElementList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="orderElementList.title"/>.pdf</display:setProperty>
</display:table>

<c:out value="${buttons}" escapeXml="false" />

<script type="text/javascript">
    highlightTableRows("orderElementList");
</script>
