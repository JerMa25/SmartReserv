package com.example.ReservationSalleMateriel.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.ReservationSalleMateriel.Message.ReservSalleRequest;
import com.example.ReservationSalleMateriel.Message.Response;
import com.example.ReservationSalleMateriel.Repository.EnseignantRepository;
import com.example.ReservationSalleMateriel.Repository.ReservSalleRepository;
import com.example.ReservationSalleMateriel.Repository.SalleDeCoursReposistory;
import com.example.ReservationSalleMateriel.Util.HoraireUtils;
import com.example.ReservationSalleMateriel.model.Enseignant;
import com.example.ReservationSalleMateriel.model.ReservSalle;
import com.example.ReservationSalleMateriel.model.SalleDeCours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ReservationSalleController {
    @Autowired
     EnseignantRepository enseigRepo;
    @Autowired
    SalleDeCoursReposistory salleRepo;

    @Autowired
    ReservSalleRepository reservSalleRepo;
   
    //http://localhost:8080/salles/available?date=2025-06-15&horaire=13:00-15:00&nbPlace=40
    @GetMapping("/salles/available")  
    public ResponseEntity<Response>getByFilter(
    @RequestParam(required = false) Integer nbPlace,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
    @RequestParam(required = false) String horaire
    ){

        Set<SalleDeCours> resultSet=new HashSet<>();

        //first check if there is no filter
        if( date==null || horaire==null ){
            //throw error message to signal that no filter has been choosen

           
            return ResponseEntity.badRequest().body(new Response<>("No filter selected", null));
            

        }else{
            //get hall that are  have nbseats> nbplace
            if(nbPlace!=null){
            resultSet=new HashSet<>(salleRepo.findByNbPlacesGreaterThanEqual(nbPlace));
        }
        else{
             // Start from all available salles if nbPlace filter is not provided
        resultSet = new HashSet<>(salleRepo.findAll());
        }
           
                //we get the salles that are reserved  for that date
                Set<SalleDeCours> reservedSalles=new HashSet<>(salleRepo.findReservedSalle(date));

                //we check if the reservedSalles have horaire that do not intersect with the horaire we want
                //first extract localtime from passed horaire
                LocalTime[]  passedTime=HoraireUtils.parseHoraire(horaire);

                 Set<SalleDeCours> reusableSalles=new HashSet<>();

                for(SalleDeCours s: reservedSalles){

                    //we get his list of reservations for that day 
                    List<ReservSalle> reservSalles=reservSalleRepo.findBySalleId(s);

                    boolean flag=true;// flag will tell us if the intervals clash

                    // we loop throught the reservations a
                    for(ReservSalle res: reservSalles){
                        
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
                        reusableSalles.add(s);
                    }
                }
                //we add all the salles that are free and those that are occupied but can be used within the horaire to thesame set
                 reusableSalles.addAll(new HashSet<>(salleRepo.findAvailableSallesForDay(date)));
                //we find the salles that are reusabe and their size fits
               
                resultSet.retainAll(reusableSalles);
                
            


        }
        

        return ResponseEntity.ok(new Response<>("available Salle based on filter", new ArrayList<>(resultSet)));

    }
        @PostMapping("/salles/reserve")
        public  ResponseEntity<Response<ReservSalle>> reserveSalle(@RequestBody ReservSalleRequest data) {
            Optional<Enseignant> enseignant =enseigRepo.findByMatricule(data.getEnseignantMatricule());
            Optional<SalleDeCours> salle=salleRepo.findById(data.getSalleId());
            if(enseignant.isPresent() && salle.isPresent()){
                ReservSalle reservSalle=new ReservSalle(LocalDate.now(),data.getDate(),data.getHoraire(),"confirmed",salle.get(),enseignant.get());
                reservSalleRepo.save(reservSalle);
               return  ResponseEntity.ok(new Response<ReservSalle>("Reservation created", reservSalle));

            }
            else{
              return   ResponseEntity.badRequest().body((new Response<ReservSalle>("Missing Fields in reservation requests", null)));

            }

            
        }
        

    
    
    


    
}
