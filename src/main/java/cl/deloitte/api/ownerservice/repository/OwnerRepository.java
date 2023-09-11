package cl.deloitte.api.ownerservice.repository;

import cl.deloitte.api.ownerservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {

    //Busca al dueño según patente
    Owner findByLicensePlate(String licensePlate);
}
