package com.flatandflatmates.JavaObjects;



import java.util.ArrayList;
import java.util.List;

public class AddressComponent {

    private String long_name;
    private String short_name;
    private List<String> types = new ArrayList<String>();

    /**
     *
     * @return
     * The long_name
     */
    public String getLong_name() {
        return long_name;
    }

    /**
     *
     * @param long_name
     * The long_name
     */
    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    /**
     *
     * @return
     * The short_name
     */
    public String getShort_name() {
        return short_name;
    }

    /**
     *
     * @param short_name
     * The short_name
     */
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    /**
     *
     * @return
     * The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

}