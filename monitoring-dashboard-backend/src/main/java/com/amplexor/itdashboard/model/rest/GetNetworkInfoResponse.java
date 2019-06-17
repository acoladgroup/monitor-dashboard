package com.amplexor.itdashboard.model.rest;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GetNetworkInfoResponse {
    @JsonProperty("return")
    private Return result;

    public Return getResult() {
        return result;
    }

    public void setResult(Return result) {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [return = " + result + "]";
    }
}
