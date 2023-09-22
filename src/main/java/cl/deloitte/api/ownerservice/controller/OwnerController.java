package cl.deloitte.api.ownerservice.controller;

import cl.deloitte.api.ownerservice.model.Owner;
import cl.deloitte.api.ownerservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerRepository repo;

    //GET obtengo todos los objetos.
    @CrossOrigin(origins = "http://clw2101334:4200/")
    @GetMapping("/all")
    public List<Owner> getAllOwners(){
        List<Owner> owners = repo.findAll();
        return owners;
    }

    //GET obtengo propietario en base a placa patente
    @CrossOrigin(origins = "http://clw2101334:4200/")
    @GetMapping("/license-plate/{licensePlate}")
    public ResponseEntity<Owner> getOwnerByLicensePlate(@PathVariable String licensePlate){

        //Creo instancia para asignar valor
        Owner owner = repo.findByLicensePlate(licensePlate);


        if (owner != null){
            //Si dueño es distinto a nulo, retorna json con datos
            return ResponseEntity.ok(owner);
        } else {
            //de lo contrario, devuelve HTTP404.
            return ResponseEntity.notFound().build();
        }
    }

    // PUT actualizo numero y correo
    @CrossOrigin(origins = "http://clw2101334:4200/")
    @PutMapping("/update/{ownerId}")
    public ResponseEntity<Object> updateOwnerContactInfo (@PathVariable String ownerId, @RequestBody Owner updatedOwner){

        //Creo instancia para asignar valor
        Owner owner = repo.findById(ownerId).orElse(null);
        // Si dueño es nulo, devuelve not found.
        if (owner == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Dueño no encontrado\", \"status\": \"error\", \"changes\": false}");
        }

        boolean changes = false;

        //pregunto si el numero actual es distinto al que se le asignará
        if (!owner.getPhoneNumber().equals(updatedOwner.getPhoneNumber())){
            owner.setPhoneNumber(updatedOwner.getPhoneNumber());
            changes = true;
        }
        //pregunto si el email actual es distinto al que se le asignará
        if (!owner.getEmail().equals(updatedOwner.getEmail())){
            owner.setEmail(updatedOwner.getEmail());
            changes = true;
        }

        if (changes){
            //Guarda cambios
            repo.save(owner);
        }

        String message = changes ? "\"Actualizacion exitosa\"" : "\"No se realizaron cambios\"";

        //Retorna json con exito
        return ResponseEntity.status(HttpStatus.OK).body("{\"owner\": " + owner.toString() + ", \"message\":" + message+ ", \"status\": \"success\", \"changes\": " + changes+"}");
    }
}
