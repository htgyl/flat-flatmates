package com.flatandflatmates.JavaObjects;


import java.util.ArrayList;
import java.util.List;

public class PlaceDetailsResult {

    private List<AddressComponent> address_components = new ArrayList<AddressComponent>();
    private String formatted_address;
    private GeometryLocation geometry;
    private String icon;
    private String id;
    private String name;
    private String place_id;
    private String reference;
    private String scope;
    private List<String> types = new ArrayList<String>();
    private String url;
    private int utc_offset;
    private String vicinity;

    /**
     *
     * @return
     * The address_components
     */
    public List<AddressComponent> getAddressComponent() {
        return address_components;
    }

    /**
     *
     * @param address_components
     * The address_components
     */
    public void setAddressComponent(List<AddressComponent> address_components) {
        this.address_components = address_components;
    }

    /**
     *
     * @return
     * The formatted_address
     */
    public String getFormatted_address() {
        return formatted_address;
    }

    /**
     *
     * @param formatted_address
     * The formatted_address
     */
    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    /**
     *
     * @return
     * The geometry
     */
    public GeometryLocation getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(GeometryLocation geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     *
     * @return
     * The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     *
     * @param scope
     * The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
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

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The utc_offset
     */
    public int getUtc_offset() {
        return utc_offset;
    }

    /**
     *
     * @param utc_offset
     * The utc_offset
     */
    public void setUtc_offset(int utc_offset) {
        this.utc_offset = utc_offset;
    }

    /**
     *
     * @return
     * The vicinity
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     *
     * @param vicinity
     * The vicinity
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

}