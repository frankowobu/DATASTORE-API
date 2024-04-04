package org.example.TentacleDocStore;

import lombok.Data;

@Data
public class RetrieveDocumentRequest {
    String DOC_STORE_ID;

    public String getDOC_STORE_ID() {
        return DOC_STORE_ID;
    }

    public void setDOC_STORE_ID(String DOC_STORE_ID) {
        this.DOC_STORE_ID = DOC_STORE_ID;
    }
}