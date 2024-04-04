package org.example.TentacleDocStore;

import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import java.util.Map;

public class PrepareDocumentForRetrieve implements FlowBean {
    public FlowExchange process(FlowExchange exchange) throws Exception {
        Map<String, Object> xchgHeader = exchange.getHeader();
        RetrieveDocumentRequest request = (RetrieveDocumentRequest)exchange.getBody();

        String filter_criteria = request.getDOC_STORE_ID();

        xchgHeader.put("DOC_STORE_ID", filter_criteria);

        exchange.setBody(filter_criteria);
        return exchange;
    }
}