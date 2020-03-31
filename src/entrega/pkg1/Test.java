package entrega.pkg1;
import jfxpaddle.AppPadelController;
import jfxpaddle.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import model.Booking;
import model.Court;
import model.Member;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author choco
 */
public class Test {

    private static boolean firstTime = true;

    public static void initMisReservas() {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.MIN, LocalTime.MIN);
        LocalTime time = LocalTime.of(20, 11, 12);
        LocalDate date = LocalDate.of(20, 11, 15);
        Court court = new Court("3");
        Member member = new Member("juani", "w", "3424", "ffs", "gfs", "23434234", null, null);
        LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.MIN, LocalTime.MIN);
        LocalTime time2 = LocalTime.of(10, 43, 12);
        LocalDate date2 = LocalDate.of(20, 11, 15);
        Court court2 = new Court("1");
        Member member2 = new Member("pepi", "w", "3424", "ffs", "gfs", "23434234", null, null);
        if (firstTime) {
            MisReservasController.setData(FXCollections.observableArrayList(
                    new Booking(dateTime, date, time, true, court, member),
                    new Booking(dateTime2, date2, time2, true, court2, member2)
            ));
            firstTime = false;
        }

    }

}
