<#if parameters.readonly?default(false)>
<label <#rt/>
 id="${parameters.id?html}" <#rt/>
    <#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
    </#if>
><#rt/>
<@s.property value="parameters.amount"/><#rt/>
</label>
<label<#rt/>
        <#if parameters.id?exists>
 id="${parameters.id?html}.currency"<#rt/>
        </#if>
<#rt/>
        <#if parameters.currencyCssClass?exists>
 class="${parameters.currencyCssClass?html}"<#rt/>
        <#else>
 class="currency"<#rt/>
        </#if>
><#rt/>
<#rt/>
        <#if parameters.currency?exists>
<@s.property value="parameters.currency"/><#rt/>
        </#if>
</label>
<#rt/>
<#else>
<#rt/>
<input type="text"<#rt/>
 name="${parameters.name?default("")?html}.amount"<#rt/>
    <#if parameters.get("size")?exists>
 size="${parameters.get("size")?html}"<#rt/>
    </#if>

    <#if parameters.maxlength?exists>
 maxlength="${parameters.maxlength?html}"<#rt/>
    </#if>

    <#if parameters.amount?exists>
 value="<@s.property value="parameters.amount"/>"<#rt/>
    </#if>

    <#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
    </#if>

    <#if parameters.readonly?default(false)>
 readonly="readonly"<#rt/>
    </#if>

    <#if parameters.tabindex?exists>
 tabindex="${parameters.tabindex?html}"<#rt/>
    </#if>

    <#if parameters.id?exists>
 id="${parameters.id?html}"<#rt/>
    </#if>

    <#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
    </#if>

    <#if parameters.cssStyle?exists>
 style="${parameters.cssStyle?html}"<#rt/>
    </#if>

    <#if parameters.title?exists>
 title="${parameters.title?html}"<#rt/>
    </#if>

    <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
    <#include "/${parameters.templateDir}/simple/common-attributes.ftl" />
/>

<select<#rt/>
 name="${parameters.name?default("")?html}.currency"<#rt/>
    <#if parameters.currencyCssClass?exists>
 class="${parameters.currencyCssClass?html}"<#rt/>
    <#else>
 class="desc"<#rt/>
    </#if>

    <#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
    </#if>

    <#if parameters.id?exists>
 id="${parameters.id?html}.currency"<#rt/>
    </#if>

    <#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
    </#if>

    <#if parameters.cssStyle?exists>
 style="${parameters.cssStyle?html}"<#rt/>
    </#if>

    <#if parameters.title?exists>
 title="${parameters.title?html}"<#rt/>
    </#if>

    <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
    <#include "/${parameters.templateDir}/simple/common-attributes.ftl" />
>
    <#if parameters.emptyOption?default(false)>
    <option value=""></option>
    </#if>
<@s.iterator value="parameters.currencyList">
    <#if parameters.currencyListKey?exists>
        <#if stack.findString(parameters.currencyListKey)?exists>
              <#assign itemKey = stack.findString(parameters.currencyListKey).toString()/>
        <#else>
              <#assign itemKey = ''/>
        </#if>
    <#else>
        <#assign itemKey = stack.findValue('top').toString()/>
    </#if>

    <#if parameters.currencyListValue?exists>
        <#if stack.findString(parameters.currencyListValue)?exists>
              <#assign itemValue = stack.findString(parameters.currencyListValue)/>
        <#else>
              <#assign itemValue = ''/>
        </#if>
    <#else>
        <#assign itemValue = stack.findString('top')/>
    </#if>
    <option value="${itemKey?html}"<#rt/>
    >${itemValue?html}</option><#lt/>
</@s.iterator>

    <#include "/${parameters.templateDir}/simple/optgroup.ftl" />
</select>
<#rt/>
</#if>
