package co.simplon.evaluation;

import co.simplon.evaluation.dao.CityRepository;
import co.simplon.evaluation.dao.HotelRepository;
import co.simplon.evaluation.dao.RoomRepository;
import co.simplon.evaluation.entities.Hotel;
import co.simplon.evaluation.entities.City;
import co.simplon.evaluation.entities.Room;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EvaluationApplication implements CommandLineRunner {

	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(EvaluationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Hotel.class, City.class);
		Random rnd = new Random();
		int max = 3;
		int min = 1;
		cityRepository.save(new City(null,"la reynerie",null));
		cityRepository.save(new City(null,"les izards",null));
		cityRepository.save(new City(null,"empalot",null));

		cityRepository.findAll().forEach(c->{
			for (int i = 0; i <5 ; i++) {
				Hotel p=new Hotel();
				p.setName(RandomString.make(18));
				p.setAddress(RandomString.make(18));
				p.setPhone((rnd.nextInt(7)+1) * 1001060601);
				p.setStar(rnd.nextInt(max - min + 1) + min);
				p.setSelected(rnd.nextBoolean());
				p.setCity(c);
				p.setPhoto("h1.jpg");
				hotelRepository.save(p);
			}
		});
		hotelRepository.findAll().forEach(c->{
			for (int i = 0; i <2 ; i++) {
				roomRepository.save(new Room(null,"simple",null,rnd.nextBoolean(),rnd.nextBoolean(), (double) (10+rnd.nextInt(100)),c));
				roomRepository.save(new Room(null,"double",null,rnd.nextBoolean(),rnd.nextBoolean(), (double) (10+rnd.nextInt(100)),c));
				roomRepository.save(new Room(null,"triple",null,rnd.nextBoolean(),rnd.nextBoolean(), (double) (10+rnd.nextInt(100)),c));
			}
		});

	}
}
