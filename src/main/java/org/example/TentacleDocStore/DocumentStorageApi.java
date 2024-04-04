
package org.example.TentacleDocStore;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionsystems.oibflow.core.FlowBean;
import com.unionsystems.oibflow.core.FlowExchange;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

public class DocumentStorageApi implements FlowBean {

    @Override
    public FlowExchange process(FlowExchange exchange) throws Exception {
        Map<String, Object> xchgHeader = exchange.getHeader();
        String directory = xchgHeader.get("PATHTOSTORE").toString();
        String payload = exchange.getBody().toString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        DocumentDto documentDto = mapper.readValue(payload, DocumentDto.class);

        String docId = documentDto.getDocumentId();
        String docLabel = documentDto.getDocumentLabel();
        String docType = documentDto.getDocumentType();
        String base64Data = documentDto.getDocumentData();
        String mimeType = documentDto.getDocumentMimeType();
        String appl_cd = documentDto.getDocumentLabel();
        String appl_nm = documentDto.getDocumentLabel();

        // Generate unique identifier
        String uid = UUID.randomUUID().toString();

        // Decode Base64 data
        byte[] decodedData = Base64.getDecoder().decode(base64Data);

        // Get extension from docType
        String extension = getExtension(docType);

        // Create filename
        String fileName = uid + "." + extension;

        // Create File object
        File file = new File(directory,fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Write data to file
            fos.write(decodedData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update headers with storage information
        xchgHeader.put("APPL_CD", appl_cd);
        xchgHeader.put("APPL_NM", appl_nm);
        xchgHeader.put("STORE_PATH", file.getAbsolutePath());
        xchgHeader.put("STORE_TYPE", "FILE");

        // Update headers with document information
        xchgHeader.put("DOC_SOURCE_ID", docId);
        xchgHeader.put("DOC_LABEL", docLabel);
        xchgHeader.put("DOC_MIME_TYPE", mimeType);
        xchgHeader.put("DOC_STORE_ID", uid);

        exchange.setBody(file);
        return exchange;
    }


    private String getExtension(String docType) {
        int index = docType.lastIndexOf('.');
        if (index > 0) {
            return docType.substring(index + 1);
        } else {
            return "";
        }
    }
}