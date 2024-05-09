package tomasz.morgas.siiZadanie.service.purchaseSercive;

import org.springframework.data.jpa.repository.JpaRepository;
import tomasz.morgas.siiZadanie.entity.PurchaseInformation;

public interface PurchaseInformationRepository extends JpaRepository<PurchaseInformation, Integer> {
}
