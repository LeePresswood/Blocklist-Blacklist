package com.leepresswood.adaware.job.country;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leepresswood.adaware.job.blocklist.Blocklist;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "blocklist", "geoname_id", "locale_code", "is_in_european_union", "continent_code", "country_iso_code"})
public class Country {
    @Id
    public Long geoname_id;

    public String locale_code;
    public String continent_code;
    public String continent_name;
    public String country_iso_code;
    public String country_name;
    public int is_in_european_union;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Blocklist> blocklist;

    public Country() {

    }

    public Long getGeoname_id() {
        return geoname_id;
    }

    public void setGeoname_id(Long geoname_id) {
        this.geoname_id = geoname_id;
    }

    public String getLocale_code() {
        return locale_code;
    }

    public void setLocale_code(String locale_code) {
        this.locale_code = locale_code;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }

    public String getCountry_iso_code() {
        return country_iso_code;
    }

    public void setCountry_iso_code(String country_iso_code) {
        this.country_iso_code = country_iso_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getIs_in_european_union() {
        return is_in_european_union;
    }

    public void setIs_in_european_union(int is_in_european_union) {
        this.is_in_european_union = is_in_european_union;
    }

    public List<Blocklist> getBlocklist() {
        return blocklist;
    }

    public void setBlocklist(List<Blocklist> blocklist) {
        this.blocklist = blocklist;
    }
}
