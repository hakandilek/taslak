<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="productSupplierList.title"/></title>
    <meta name="heading" content="<fmt:message key='productSupplierList.heading'/>"/>
    <meta name="menu" content="ProductSupplierMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
           onclick="location.href='<c:url value="/ProductSupplier/editProductSupplier.do"/>'"
           value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.do"/>'"
           value="<fmt:message key="button.done"/>"/>
</c:set>

<c:set var="deleteChecks">
	<input type="checkbox" name="allbox" 
		   onclick="checkAll(document.getElementById('productSuppliersList'), 'deleteId')" />
</c:set>

<s:form id="productSuppliersForm" namespace="/ProductSupplier" action="productSuppliers">
    <s:submit key="button.search" align="left"/>
</s:form>

<s:form id="productSuppliersList" namespace="/ProductSupplier" action="productSuppliers">
    <display:table name="productSuppliers" class="table" requestURI="" id="productSupplierList" export="true" pagesize="25"
            decorator="org.xmdl.ida.lib.web.decorator.BeanDecorator">

        <display:column property="id" media="csv excel xml pdf" titleKey="productSupplier.id"/>
        <display:column property="product.id" sortable="true" titleKey="productSupplier.product"/>
        <display:column property="supplier.id" sortable="true" titleKey="productSupplier.supplier"/>

        <display:column media="html" titleKey="List.Edit" >
            <a href="/ProductSupplier/editProductSupplier.do?id=<c:out value="${productSupplierList.id}"/>"><ida:base-url context="icon" path="edit.gif"/></a>
        </display:column>
        <display:column media="html" titleKey="button.copy">
            <a href="/ProductSupplier/copyProductSupplier.do?idToCopy=<c:out value="${productSupplierList.id}"/>"><ida:base-url context="icon" path="save.gif"/></a>
        </display:column>
        <display:column property="deleteCheckbox" media="html" title="${deleteChecks}"/>

		<display:footer>
			<tr class="footer">
                <!-- change the "colspan" value below according to the number of columns -->
				<td colspan="5">&nbsp;</td>
				<td>
					<input id="productSupplierDeleteButton" name="method:deleteMass" 
						   value="<fmt:message key="button.delete"/>" 
						   onclick="return confirmDelete('ProductSupplier')" type="submit" />
				</td>
			</tr>
		</display:footer>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="productSupplierList.productSupplier"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="productSupplierList.productSuppliers"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="productSupplierList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="productSupplierList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="productSupplierList.title"/>.pdf</display:setProperty>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

    <!--<script type="text/javascript">-->
    <!--highlightTableRows("productSupplierList");-->
    <!--</script>-->

</s:form>