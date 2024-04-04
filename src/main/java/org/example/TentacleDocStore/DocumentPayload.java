package org.example.TentacleDocStore;

import lombok.Data;

@Data
public class DocumentPayload {
    private String documentId;
    private String documentLabel;
    private String documentData;
    private String documentType;
    private String documentMimeType;

}