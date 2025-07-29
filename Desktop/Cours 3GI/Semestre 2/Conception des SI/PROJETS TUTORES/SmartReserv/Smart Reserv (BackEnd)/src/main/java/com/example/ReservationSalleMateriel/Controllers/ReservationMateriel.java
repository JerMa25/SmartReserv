package com.example.ReservationSalleMateriel.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.ReservationSalleMateriel.Message.Response;
import com.example.ReservationSalleMateriel.Repository.ReservMaterielleRepository;
import com.example.ReservationSalleMateriel.Repository.VideoProjectorRepository;
import com.example.ReservationSalleMateriel.Util.HoraireUtils;
import com.example.ReservationSalleMateriel.model.ReservMat;
import com.example.ReservationSalleMateriel.model.ReservSalle;
import com.example.ReservationSalleMateriel.model.SalleDeCours;
import com.example.ReservationSalleMateriel.model.VideoProj;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ReservationMateriel {
    @Autowired
    VideoProjectorRepository videoRepo;

    @Autowired
    ReservMaterielleRepository reservMatRepo;

    @GetMapping("/materiel/projectors")
    public ResponseEntity<Response> getProjectors(
       @RequestParam(required = false) String model,
    @RequestParam(required = false) 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
    @RequestParam(required = false) String horaire
    ) {
        if(date==null || horaire==null){
            return ResponseEntity.badRequest().body(new Response<>("missing date or horaire filter", null));

        }
        else{

            //validate that horaire is in a valid format
            if(!HoraireUtils.validateHoraire(horaire)){
                 return ResponseEntity.badRequest().body(new Response<>("bad time(horaire) format", null));

            }


            Set<VideoProj> resultSet=new HashSet<>();

            //search by model if it was passed
            //initialize the result with videoprojectors that have the required model
            //else initialize it with all the video projectors
            if(model!=null){
                for(VideoProj v: videoRepo.findAll()){
                    if(v.getMarque_et_modele().toLowerCase().startsWith(model.toLowerCase())){
                        resultSet.add(v);
                    }
                }
            }else{
                resultSet.addAll(new HashSet<>(videoRepo.findAll()));
            }

        
        
            
           
                //we get the  projectors that are reserved  for that date
                Set<VideoProj> reservedVideoProj=new HashSet<>(videoRepo.findReservedVideoProjsForDay(date));

                //we check if the reserved projectors have horaire that do not intersect with the horaire we want
                //first extract localtime from passed horaire
                LocalTime[]  passedTime=HoraireUtils.parseHoraire(horaire);

                 Set<VideoProj> reusableVideoProj=new HashSet<>();

                for(VideoProj s: reservedVideoProj){

                    //we get his list of reservations for that particular projector
                    List<ReservMat> reservMat=reservMatRepo.findByMatId(s);

                    boolean flag=true;// flag will tell us if the intervals clash

                    // we loop throught the reservations a
                    for(ReservMat res: reservMat){
                        
                        //we get the localtime for  the reservationo
                        LocalTime[] reservHoraire=HoraireUtils.parseHoraire(res.getHoraire());
                        if(!(reservHoraire[0].isAfter(passedTime[1] ) || reservHoraire[1].isBefore(passedTime[0]))){
                            //we check if the start time of the reservation is after the end time of the event we want to do or
                            //if its end time is before the start time of the event we want to do
                            //we do not to check if both conditions failed
                            flag=false;
                        }
                    }
                    if(flag==true){
                        reusableVideoProj.add(s);
                    }
                }
                //we add all the salles that are free and those that are occupied but can be used within the horaire to thesame set
                 reusableVideoProj.addAll(new HashSet<>(videoRepo.findAvailableVideoProjectorsForDay(date)));
                //we find the salles that are reusabe and their size fits
               
                resultSet.retainAll(reusableVideoProj);

                return ResponseEntity.ok(new Response<>("available video projectors based on filters", new ArrayList<>(resultSet)));
                
            


    }
    
}
}