package co.full.talks.springdata;

import com.google.cloud.datastore.LatLng;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ShellComponent
@SpringBootApplication
public class SpringDataApplication {

	@Autowired
	CarRepository bookRepository;
	Gson gson = new Gson();

	Car car = new Car();

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@ShellMethod("create data")
	public void createData(){
		bookRepository.saveAll(IntStream.range(1, 10).mapToObj(i->{
			return new Car(Long.valueOf(i),i%2==0 ? "square" : "circle","kl52D"+i,i, Double.valueOf(i),Double.valueOf(i));
		}).collect(Collectors.toList()));
	}

	@ShellMethod("fetch all cars")
	public String findAll() {
		System.out.println(" displaying all the entities " );
		return gson.toJson(this.bookRepository.findAll());
	}

	@ShellMethod("fetch by Vin")
	public String findByVin(String vin) {
		System.out.println(" displaying entity by vin : " + vin );
		return gson.toJson(this.bookRepository.findByVin(vin));

	}

	@ShellMethod("fetch by meta")
	public String findByMeta(String meta) {
		System.out.println(" displaying entity by vin : " + meta );
		return gson.toJson(this.bookRepository.findByMeta(meta));
	}


	@ShellMethod("fetch by latlng")
	public String findByLatLng(double lat, double lng) {
		return gson.toJson(this.bookRepository.findByLatLngGreaterThan(LatLng.of(lat,lng)));
	}

	@ShellMethod("delete by Vin")
	public String deleteByVin(String vin) {
		this.bookRepository.deleteByVin(vin);
		return gson.toJson(this.bookRepository.findAll());
	}

	@ShellMethod("delete all")
	public String deleteAll() {
		this.bookRepository.deleteAll();
		return gson.toJson(this.bookRepository.findAll());
	}


}
