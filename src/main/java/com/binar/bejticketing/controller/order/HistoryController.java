package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.service.BookingService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    @Value("${CLOUDINARY_URL}")
    Cloudinary cloudinary;

    @GetMapping
    public ResponseEntity<ResponseData<List<Booking>>> getAllHistoryBooking(){
        ResponseData<List<Booking>> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(bookingService.getAllBooking());
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Booking>> getHistoryBookingById(@PathVariable("id") Long id){
        ResponseData<Booking> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(bookingService.getBookingById(id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/user/{id-user}")
    public ResponseEntity<ResponseData<List<Booking>>> getHistoryBookingByIdUser(@PathVariable("id-user") Long idUser){
        ResponseData<List<Booking>> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(bookingService.getBookingHistoryById(idUser));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update/{id-booking}")
    public ResponseEntity<ResponseData<Booking>> editHistoryBooking(@PathVariable("id-booking")Long idBooking,
                                                                    @RequestParam("state") boolean state){
        ResponseData<Booking> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(bookingService.updateStatePaymentBooking(idBooking, state));
        return ResponseEntity.ok(responseData);
    }
    @PutMapping("/update/photo/{id-booking}")
    public ResponseEntity<ResponseData<Booking>> editPhotoHistoryBooking(@PathVariable("id-booking")Long idBooking,
                                                                         @RequestParam("picture-url") MultipartFile file)
            throws IOException {
        byte[] bit = file.getBytes();
        Files.write(Path.of("C:\\Users\\fathanazka\\Downloads\\diamond.png"),bit);

        String filename = String.valueOf(UUID.randomUUID());
        cloudinary.uploader().upload(new File("C:\\Users\\fathanazka\\Downloads\\diamond.png"),
                ObjectUtils.asMap("public_id", filename));

        String url = cloudinary.url().imageTag(filename);
        ResponseData<Booking> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(bookingService.updatePictureBooking(idBooking, url));
        return ResponseEntity.ok(responseData);
    }

}
