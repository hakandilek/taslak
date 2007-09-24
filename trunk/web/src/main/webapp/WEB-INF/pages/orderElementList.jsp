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

    <s:textfield key="orderElementSearch.fromQuantity" cssClass="text medium"/>
    <s:textfield key="orderElementSearch.toQuantity" cssClass="text medium"/>
    <s:select name="orderElementSearch.order.id" list="orderList" listKey="id" listValue="id"
        key="orderElement.order" headerKey="<%=""+Integer.MIN_VALUE%>" headerValue="Select.all"/>
    <s:select name="orderElementSearch.product.id" list="productList" listKey="id" listValue="id"
        key="orderElement.product" headerKey="<%=""+Integer.MIN_VALUE%>" headerValue="Select.all"/>
    <s:submit  key="button.search"/>


<display:table name="orderElements" class="table" requestURI="" id="orderElementList" export="true" pagesize="25"
        decorator="org.xmdl.taslak.webapp.decorator.BeanDecorator">
    <display:column property="id" sortable="true" titleKey="orderElement.id"
                    href="editOrderElement.html" media="html" paramId="id" paramProperty="id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="orderElement.id"/>
    <display:column property="quantity" sortable="true" titleKey="orderElement.quantity"/>
    <display:column property="order.id" sortable="true" titleKey="orderElement.order"
                    href="editOrder.html?" paramId="order.id" paramProperty="order.id"/>
    <display:column property="product.id" sortable="true" titleKey="orderElement.product"
                    href="editProduct.html?" paramId="product.id" paramProperty="product.id"/>
    <display:column titleKey="button.copy" >
        <a href="/copyOrderElement.html?idToCopy=<c:out value="${orderElementList.id}"/>"><img src="/images/common/save.gif"></a>
    </display:column>

    <display:column property="deleteCheckbox" media="html"
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