package com.flatandflatmates.JavaObjects;

import java.util.ArrayList;
import java.util.List;

public class PlaceDetail {

    private List<Object> html_attributions = new ArrayList<Object>();
    private PlaceDetailsResult result;
    private String status;

    /**
     *
     * @return
     * The html_attributions
     */
    public List<Object> getHtml_attributions() {
        return html_attributions;
    }

    /**
     *
     * @param html_attributions
     * The html_attributions
     */
    public void setHtml_attributions(List<Object> html_attributions) {
        this.html_attributions = html_attributions;
    }

    /**
     *
     * @return
     * The result
     */
    public PlaceDetailsResult getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(PlaceDetailsResult result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}