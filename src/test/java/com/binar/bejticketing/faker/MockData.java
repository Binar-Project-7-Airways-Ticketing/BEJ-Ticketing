package com.binar.bejticketing.faker;

import com.binar.bejticketing.entity.*;
import com.binar.bejticketing.utils.Gender;
import com.binar.bejticketing.utils.PaymentMethod;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.binar.bejticketing.utils.AgeCategoryName.CHILDREN;
import static com.binar.bejticketing.utils.SeatUtils.AVAILABLE;
import static com.binar.bejticketing.utils.SpecialRequest.NONE;

public class MockData {
    public Payment mockDataPayment(Payment payment){

        Date parse = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        payment.setIdPayment(1L);
        payment.setPaymentMethod(PaymentMethod.BRI);
        payment.setCreatedAt(parse);
        payment.setUpdatedAt(null);
        return payment;
    }
    public Passenger mockDataPassenger(Passenger passenger){

        Date parse = null;
        Date parseBirthday = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
            parseBirthday = new SimpleDateFormat("MM/dd/yyyy").parse("12/07/2001");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        passenger.setIdPassenger(1L);
        passenger.setPassport("0829102");
        passenger.setBirthday(parseBirthday);
        passenger.setDeleted(false);
        passenger.setContactNumber("08232368823");
        passenger.setFirstName("Fathan");
        passenger.setLastName("Azka");
        passenger.setGender(Gender.PRIA);
        passenger.setNationality("INDONESIA");
        passenger.setSpecialRequest(NONE);
        passenger.setCreatedAt(parse);

        return  passenger;
    }
    public AgeCategory mockDataAgeCategory(AgeCategory ageCategory){
        Date parse = null;
        Date parseBirthday = null;
        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
            parseBirthday = new SimpleDateFormat("MM/dd/yyyy").parse("12/07/2001");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ageCategory = AgeCategory.builder().idCategory(2L).nameCategory(CHILDREN).price(BigInteger.valueOf(10000)).createdAt(parse).updatedAt(null).build();

        return ageCategory;
    }
    public Seat mockDataSeat(Seat seat){
        Date parse = null;

        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        seat = Seat.builder().idSeat(1L).numberSeat("A01").stateSeat(AVAILABLE).createdAt(parse).updatedAt(null).build();

        return seat;
    }
    public Luggage mockDataLuggage(Luggage luggage){
        Date parse = null;

        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        PlaneDetails planeDetails = new PlaneDetails();
        planeDetails = mockDataPlaneDetail(planeDetails);

        luggage.setIdLuggage(1L);
        luggage.setCapacity(BigInteger.valueOf(30));
        luggage.setPrice(BigInteger.valueOf(173000));
        luggage.setReady(false);
        luggage.setPlaneDetails(planeDetails);
        luggage.setCreatedAt(parse);
        return luggage;
    }
    public PlaneDetails mockDataPlaneDetail(PlaneDetails planeDetails){
        Date parse = null;

        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        planeDetails.setIdPlaneClass(1L);
        planeDetails.setPlaneClass("ECONOMY");
        planeDetails.setPrice(10000L);

        return planeDetails;
    }

    public Flight mockDataFlight(Flight flight) throws ParseException {
        Date parse = null;

        try {
            parse = new SimpleDateFormat("dd-MM-yyyy hh:MM:ss").parse("12-07-2001 12:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        flight.setIdFlight(1L);
        flight.setCreatedAt(parse);
        flight.setArrivalCode("JKT");
        flight.setFlightNumber("001");
        flight.setPrice(10000L);
        flight.setArrivalDate(new SimpleDateFormat("MM/dd/yyyy").parse("12/07/2001"));
        flight.setArrivalTime(new SimpleDateFormat("hh:mm").parse("12:07"));
        flight.setDepartureCode("LMP");
        flight.setDepartureTime(new SimpleDateFormat("hh:mm").parse("02:07"));
        flight.setIsActive(true);

        return flight;
    }
}
