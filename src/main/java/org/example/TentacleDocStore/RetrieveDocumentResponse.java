package org.example.TentacleDocStore;

import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@Slf4j
public class RetrieveDocumentResponse implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        Map<String, Object> dbResponse = exchange.getHeader();
        String storagePath = dbResponse.get("STORE_PATH").toString();

        log.info("******" + storagePath);

        byte[] fileBytes = Files.readAllBytes(Paths.get(storagePath));
        String base64Content = Base64.getEncoder().encodeToString(fileBytes);

        String output = "data: "+ base64Content;
        exchange.setBody(output);

        return exchange;
    }
}