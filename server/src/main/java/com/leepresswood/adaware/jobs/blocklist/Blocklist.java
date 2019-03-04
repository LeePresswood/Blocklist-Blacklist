package com.leepresswood.adaware.jobs.blocklist;

import com.leepresswood.adaware.jobs.country.Country;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Blocklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String network;
    public Long geoname_id;
    public Long registered_country_geoname_id;
    public String represented_country_geoname_id;
    public String is_anonymous_proxy;
    public String is_satellite_provider;

//    @ManyToOne(optional = false, targetEntity = Country.class)
//    @JoinColumn(name = "geoname_id", insertable = false, updatable = false)
//    public Country country;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geoname_id", insertable = false, updatable = false)
    public Country country;

//    @ManyToOne(optional = false, targetEntity = Country.class)
//    @JoinColumn(name = "registered_country_geoname_id")
//    public Long registered_country_geoname_id;

    public Blocklist() {

    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

//    public String getRegistered_country_geoname_id() {
//        return registered_country_geoname_id;
//    }
//
//    public void setRegistered_country_geoname_id(String registered_country_geoname_id) {
//        this.registered_country_geoname_id = registered_country_geoname_id;
//    }

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

    public Long getRegistered_country_geoname_id() {
        return registered_country_geoname_id;
    }

    public void setRegistered_country_geoname_id(Long registered_country_geoname_id) {
        this.registered_country_geoname_id = registered_country_geoname_id;
    }

    public Long getGeoname_id() {
        return geoname_id;
    }

    public void setGeoname_id(Long geoname_id) {
        this.geoname_id = geoname_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}