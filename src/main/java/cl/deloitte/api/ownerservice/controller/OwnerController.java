package cl.deloitte.api.ownerservice.controller;

import cl.deloitte.api.ownerservice.model.Owner;
import cl.deloitte.api.ownerservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerRepository repo;

    //GET obtengo propietario en base a placa patente
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
    @PutMapping("/update/{ownerId}")
    public ResponseEntity<Owner> updateOwnerContactInfo (@PathVariable String ownerId, @RequestBody Owner updatedOwner){

        //Creo instancia para asignar valor
        Owner owner = repo.findById(ownerId).orElse(null);
        // Si dueño es nulo, devuelve HTTP404.
        if (owner == null){
            return ResponseEntity.notFound().build();
        }
        //Si dueño no es null, se actualizan campos email y phoneNumber.
        owner.setPhoneNumber(updatedOwner.getPhoneNumber());
        owner.setEmail(updatedOwner.getEmail());

        //Guarda cambios
        repo.save(owner);

        //Retorna json con cambios
        return ResponseEntity.ok(owner);
    }
}
