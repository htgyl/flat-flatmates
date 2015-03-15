package com.flatandflatmates.JavaObjects;

/**
 * Created by applect on 15/3/15.
 */

public class Prediction {

    private String description;
    private String id;
    private String place_id;
    private String reference;

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The place_id
     */
    public String getPlace_id() {
        return place_id;
    }

    /**
     *
     * @param place_id
     * The place_id
     */
    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    /**
     *
     * @return
     * The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference
     * The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}