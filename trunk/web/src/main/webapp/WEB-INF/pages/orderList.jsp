<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderList.title"/></title>
    <meta name="heading" content="<fmt:message key='orderList.heading'/>"/>
    <meta name="menu" content="OrderMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
        onclick="location.href='<c:url value="/editOrder.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<s:form id="ordersForm" action="orders">
    <s:textfield key="orderSearch.name" cssClass="text medium"/>
    <s:textfield key="orderSearch.fromPriceTotals" cssClass="text medium"/>
    <s:textfield key="orderSearch.toPriceTotals" cssClass="text medium"/>
    <s:textfield key="orderSearch.fromCreateDate" cssClass="text" title="date" size="11"/>
    <s:textfield key="orderSearch.toCreateDate" cssClass="text" title="date" size="11"/>
    <s:submit key="button.search"/>

<display:table name="orders" class="table" requestURI="" id="orderList" export="true" pagesize="25"
        decorator="org.xmdl.taslak.webapp.decorator.BeanDecorator">
    <display:column property="id" sortable="true" href="editOrder.html" media="html"
        paramId="id" paramProperty="id" titleKey="order.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="order.id"/>
    <display:column property="name" sortable="true" titleKey="order.name"/>
    <display:column property="priceTotals" sortable="true" titleKey="order.priceTotals"/>
    <display:column sortProperty="createDate" sortable="true" titleKey="order.createDate">
         <fmt:formatDate value="${orderList.createDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column titleKey="button.copy" media="html">
        <a href="/copyOrder.html?idToCopy=<c:out value="${orderList.id}"/>"><img src="/images/common/save.gif"></a>
    </display:column>
    <display:column property="deleteCheckbox" media="html"
            title="<input id='orders_button_' name='method:deleteMass' value='Sil' onclick=\"return confirmDelete('Order')\" type='submit' />
            <input type=\"checkbox\" name=\"allbox\" onclick=\"checkAll(document.getElementById('ordersForm'),'deleteId')\" />"/>


    <display:setProperty name="paging.banner.item_name"><fmt:message key="orderList.order"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="orderList.orders"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="orderList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="orderList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="orderList.title"/>.pdf</display:setProperty>
</display:table>

<c:out value="${buttons}" escapeXml="false" />

<!--<script type="text/javascript">-->
    <!--highlightTableRows("orderList");-->
<!--</script>-->
</s:form>

<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar-setup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/lang/calendar-en.js'/>"></script>
<script type="text/javascript">
    Calendar.setup({inputField: "ordersForm_orderSearch_fromCreateDate", ifFormat: "%d/%m/%Y", button: "orderSearch.fromCreateDateDatePicker"});
    Calendar.setup({inputField: "ordersForm_orderSearch_toCreateDate", ifFormat: "%d/%m/%Y", button: "orderSearch.toCreateDateDatePicker"});
</script>