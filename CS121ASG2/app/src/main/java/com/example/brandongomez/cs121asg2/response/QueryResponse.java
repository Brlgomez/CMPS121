package com.example.brandongomez.cs121asg2.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryResponse {

    @SerializedName("response")
    @Expose
    public Response response;

}