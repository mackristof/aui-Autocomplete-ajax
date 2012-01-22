<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<script src="/autocomplete-portlet/js/jquery-1.7.1.min.js" type="text/javascript"></script>


<h1>Aire Marine</h1>
<aui:form onSubmit="">

<div id="<portlet:namespace />AireMarineList"></div>
    
    <aui:script use="aui-autocomplete">

    var dataSource = new A.DataSource.IO(
        {
            source: '<portlet:resourceURL />'
        }
    );

    var autocomplete = new A.AutoComplete(
        {
            dataSource: dataSource,
            contentBox: '#<portlet:namespace />AireMarineList',
            matchKey: 'name',
            schema: {
                resultListLocator: 'response',
                resultFields: ['key', 'name']
            },
            schemaType:'json',
            typeAhead: true
        }
    );

    autocomplete.generateRequest = function(query) {
        return {
            request: '&query=' + query
        };
    }
    
    autocomplete.render();
    autocomplete.on('itemSelect', function(item, fullItem) {
    $('#<portlet:namespace />AireMarineID').val(fullItem['key']);
});
    
</aui:script><br>

<aui:input name="<portlet:namespace />AireMarineID" type="text" id="AireMarineID" label="ID Aire Marine"></aui:input>
</aui:form>