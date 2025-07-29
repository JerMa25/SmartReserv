package com.example.ReservationSalleMateriel.Util;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class HoraireUtils {

    public static LocalTime[] parseHoraire(String horaire) {
       

        try {
             String[] parts = horaire.split("-");
            LocalTime startTime = LocalTime.parse(parts[0].trim());
            LocalTime endTime = LocalTime.parse(parts[1].trim());

            return new LocalTime[]{startTime, endTime};
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format in horaire: " + horaire, e);
        }
    }

    public static boolean validateHoraire(String horaire){
         if (horaire == null || !horaire.contains("-")) {
            return false;
        }

        String[] parts = horaire.split("-");
        if (parts.length != 2) {
            return false;
        }
        return true;
    }
}
