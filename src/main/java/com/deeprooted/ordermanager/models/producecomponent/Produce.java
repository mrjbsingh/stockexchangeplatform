package com.deeprooted.ordermanager.models.producecomponent;

public class Produce {
    private String produceName;
    private String produceId;

    public Produce(String produceName, String produceId) {
        this.produceName = produceName;
        this.produceId = produceId;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public String getProduceId() {
        return produceId;
    }

    public void setProduceId(String produceId) {
        this.produceId = produceId;
    }

}
