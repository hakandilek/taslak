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
<s:form id="orderElementsForm" action="orderElements">
<display:table name="orderElements" class="table" requestURI="" id="orderElementList" export="true" pagesize="25"
        decorator="org.xmdl.taslak.webapp.decorator.DeleteIdDecorator">
    <display:column property="id" sortable="true" href="editOrderElement.html" media="html"
        paramId="id" paramProperty="id" titleKey="orderElement.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="orderElement.id"/>
    <display:column property="quantity" sortable="true" titleKey="orderElement.quantity"/>
    <display:column property="order.id" sortable="true" titleKey="orderElement.order"/>
    <display:column property="product.id" sortable="true" titleKey="orderElement.product"/>
    <display:column property="idCheckbox" media="html"
            title="<input id='orderElements_button_' name='method:deleteMass' value='Sil' onclick=\"return confirmDelete('OrderElement')\" type='submit' />
            <input type=\"checkbox\" name=\"allbox\" onclick=\"checkAll(document.getElementById('orderElementsForm'),'deleteId')\" />"/>


    <display:setProperty name="paging.banner.item_name"><fmt:message key="orderElementList.orderElement"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="orderElementList.orderElements"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="orderElementList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="orderElementList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="orderElementList.title"/>.pdf</display:setProperty>
</display:table>

<c:out value="${buttons}" escapeXml="false" />

<!--<script type="text/javascript">-->
    <!--highlightTableRows("orderElementList");-->
<!--</script>-->
</s:form>