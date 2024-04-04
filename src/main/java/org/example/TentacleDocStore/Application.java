package org.example.TentacleDocStore;

public class Application {
    private int id;
    private String applCd;
    private String applNm;
    private String storeType;
    private String storePath;

    // Default constructor
    public Application() {
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplCd() {
        return applCd;
    }

    public void setApplCd(String applCd) {
        this.applCd = applCd;
    }

    public String getApplNm() {
        return applNm;
    }

    public void setApplNm(String applNm) {
        this.applNm = applNm;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }
}