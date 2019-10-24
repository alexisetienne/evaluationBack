package co.simplon.evaluation.web;

import co.simplon.evaluation.dao.HotelRepository;
import co.simplon.evaluation.entities.Hotel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
public class CatalogueRestController {
    private HotelRepository hotelRepository;

    public CatalogueRestController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    @GetMapping(path="/photo/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Hotel p= hotelRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/evaluation/hotels/"+p.getPhoto()));
    }
    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
        Hotel p=hotelRepository.findById(id).get();
        p.setPhoto(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/evaluation/hotels/"+p.getPhoto()),file.getBytes());
        hotelRepository.save(p);
    }
}
