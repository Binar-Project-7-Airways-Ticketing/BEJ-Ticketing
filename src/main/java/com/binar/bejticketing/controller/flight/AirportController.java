package com.binar.bejticketing.controller.flight;

import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.entity.Airport;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.service.AirportService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/airport")
public class AirportController {


    @Autowired
    AirportService airportService;

    @Autowired
    @Value("${CLOUDINARY_URL}")
    Cloudinary cloudinary;

    @PostMapping("/create")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport){
        return new ResponseEntity<>(airportService.createAirport(airport), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Airport> updateAirport(@RequestBody Airport airport){
        return new ResponseEntity<>(airportService.updateAirport(airport), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> createAirport(@PathVariable("code")String code){
        return new ResponseEntity<>(airportService.deleteAirport(code), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirport(){
        return new ResponseEntity<>(airportService.findAllAirport(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Airport> getAirportByName(@PathVariable("name") String name){
        return new ResponseEntity<>(airportService.findAirportByName(name), HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Airport>> getAirportByCity(@PathVariable("city") String city){
        return new ResponseEntity<>(airportService.findAirportByCity(city), HttpStatus.OK);
    }

    @PostMapping(value = "/upload/{city}" )
    public ResponseEntity<ResponseData> upload(@PathVariable("city") String city , @RequestParam("image") MultipartFile file)throws IOException {
        byte[] bit = file.getBytes();
        Files.write(Path.of("src/main/java/com/binar/bejticketing/media/scenery.jpg"),bit);

        String filename = String.valueOf(UUID.randomUUID());
        cloudinary.uploader().upload(new File("src/main/java/com/binar/bejticketing/media/scenery.jpg"),
                ObjectUtils.asMap("public_id", filename));

        String url = cloudinary.url().imageTag(filename);
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(url);

        airportService.setImage(url,city);
//        String json = "{ \"url\":"+url+"}";
//        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        return ResponseEntity.ok(responseData);
    }
}
