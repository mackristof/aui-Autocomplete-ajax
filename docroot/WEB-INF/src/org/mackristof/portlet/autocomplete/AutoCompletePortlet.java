package org.mackristof.portlet.autocomplete;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Christophe Mourette
 */
public class AutoCompletePortlet extends GenericPortlet {

    private static List<ListEntry> ENTRIES = Collections.unmodifiableList(new ArrayList<ListEntry>() {{
        add(new ListEntry("HUHHSYQGYUGYQDDQY", "Aire Marine 1"));
        add(new ListEntry("HUHGHGJHGJGHJFTDTYY", "PAS Aire Marine 2"));
        add(new ListEntry("QGYYUYGUGYQDDQY", "Encore Aire Marine 3"));

    }});


    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        getPortletContext().getRequestDispatcher("/WEB-INF/jsp/view.jsp").include(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response)
            throws PortletException, IOException {
        String query = request.getParameter("query");
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        jsonObject.put("response", jsonArray);
        List<ListEntry> entries = getFilteredEntries(query);
        for (ListEntry entry : entries) {
            JSONObject jsonObjectToSend = JSONFactoryUtil.createJSONObject();
            jsonObjectToSend.put("key", entry.key);
            jsonObjectToSend.put("name", entry.name);
            jsonArray.put(jsonObjectToSend);
        }
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject.toString());
    }


    private List<ListEntry> getFilteredEntries(String query) {
        if (query == null || "*".equals(query)) {
            return ENTRIES;
        }

        List<ListEntry> result = new ArrayList<ListEntry>();

        String lowercaseQuery = query.toLowerCase();
        for (ListEntry entry : ENTRIES) {
            if (entry.name.toLowerCase().startsWith(lowercaseQuery)) {
                result.add(entry);
            }
        }
        return result;
    }
    private static class ListEntry {
        public String key;
        public String name;

        public ListEntry(String key, String name) {
            this.key = key;
            this.name = name;
        }
    }
}
