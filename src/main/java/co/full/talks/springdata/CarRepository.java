package co.full.talks.springdata;

import com.google.cloud.datastore.LatLng;
import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import com.google.cloud.spring.data.datastore.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends DatastoreRepository<Car, Long> {

    Optional<Car> findByVin(String vin);

    Optional<Car> findByLatLngGreaterThan(LatLng latLng1);

    List<Car> findByMeta(String meta);

    @Query("SELECT license FROM Car WHERE id = @id")
    String findByQuery(String id);

    void deleteByVin(String vin);
}
