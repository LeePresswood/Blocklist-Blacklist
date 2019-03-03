package com.leepresswood.adaware.jobs;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Blocklist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String network;
    public String geoname_id;
    public String registered_country_geoname_id;
    public String represented_country_geoname_id;
    public String is_anonymous_proxy;
    public String is_satellite_provider;

    public Blocklist(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getGeoname_id() {
        return geoname_id;
    }

    public void setGeoname_id(String geoname_id) {
        this.geoname_id = geoname_id;
    }

    public String getRegistered_country_geoname_id() {
        return registered_country_geoname_id;
    }

    public void setRegistered_country_geoname_id(String registered_country_geoname_id) {
        this.registered_country_geoname_id = registered_country_geoname_id;
    }

    public String getRepresented_country_geoname_id() {
        return represented_country_geoname_id;
    }

    public void setRepresented_country_geoname_id(String represented_country_geoname_id) {
        this.represented_country_geoname_id = represented_country_geoname_id;
    }

    public String getIs_anonymous_proxy() {
        return is_anonymous_proxy;
    }

    public void setIs_anonymous_proxy(String is_anonymous_proxy) {
        this.is_anonymous_proxy = is_anonymous_proxy;
    }

    public String getIs_satellite_provider() {
        return is_satellite_provider;
    }

    public void setIs_satellite_provider(String is_satellite_provider) {
        this.is_satellite_provider = is_satellite_provider;
    }
}