<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="supplierList.title"/></title>
    <meta name="heading" content="<fmt:message key='supplierList.heading'/>"/>
    <meta name="menu" content="SupplierMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
           onclick="location.href='<c:url value="/Supplier/editSupplier.do"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.do"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteChecks">
    <input type="checkbox" name="allbox" 
           onclick="checkAll(document.getElementById('suppliersList'), 'deleteId')" />
</c:set>

<s:form id="suppliersForm" namespace="/Supplier" action="suppliers">
    <s:textfield key="supplierSearch.name" cssClass="text medium" labelposition="left"/>
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="suppliersList" namespace="/Supplier" action="suppliers">
    <display:table name="suppliers" class="table" requestURI="" id="supplierList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="supplier.id"/>
        <display:column property="name" sortable="true" titleKey="supplier.name"/>
		<display:column property="contact.fullname" sortable="true" titleKey="supplier.contact.fullname"/>
		<display:column property="contact.phone" sortable="true" titleKey="supplier.contact.phone"/>

        <display:column media="html" titleKey="List.Edit" >
            <a href="/Supplier/editSupplier.do?id=<c:out value="${supplierList.id}"/>"><ida:base-url context="icon" path="edit.gif"/></a>
        </display:column>
        <display:column media="html" titleKey="button.copy">
            <a href="/Supplier/copySupplier.do?idToCopy=<c:out value="${supplierList.id}"/>"><ida:base-url context="icon" path="save.gif"/></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteChecks}"/>

        <display:footer>
            <tr class="footer">
                <!-- change the "colspan" value below according to the number of columns -->
                <td colspan="5">&nbsp;</td>
                <td>
                    <input id="supplierDeleteButton" name="method:deleteMass" 
                           value="<fmt:message key="button.delete"/>" 
                           onclick="return confirmDelete('Supplier')" type="submit" />
                </td>
            </tr>
        </display:footer>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="supplierList.supplier"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="supplierList.suppliers"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="supplierList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="supplierList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="supplierList.title"/>.pdf</display:setProperty>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

    <!--<script type="text/javascript">-->
    <!--highlightTableRows("supplierList");-->
    <!--</script>-->

</s:form>