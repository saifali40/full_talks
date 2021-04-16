package co.full.talks.springdata;

import com.google.cloud.datastore.LatLng;
import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import com.google.cloud.spring.data.datastore.core.mapping.Unindexed;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {
    @Id
    private Long id;
    private String vin;
    private String license;
    private List<String> meta;
    private LatLng latLng;

    @Unindexed
    private int color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Car(Long id, String vin, String license, Integer color, Double lat, Double lng) {
        this.id = id;
        this.vin = vin;
        this.license = license;
        this.color = color;
        this.latLng = LatLng.of(lat, lng);

        if(meta==null){
            meta=new ArrayList<>();
        }
        meta.add(id.toString());
        meta.add(vin);
        meta.add(license);
        meta.add(color.toString());
    }

    public Car() {
    }
}
