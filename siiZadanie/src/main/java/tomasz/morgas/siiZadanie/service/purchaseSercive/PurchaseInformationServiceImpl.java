package tomasz.morgas.siiZadanie.service.purchaseSercive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tomasz.morgas.siiZadanie.entity.PurchaseInformation;

import java.util.List;

@Repository
public class PurchaseInformationServiceImpl implements  PurchaseInformationService{
    private PurchaseInformationRepository purchaseInformationRepository;

    @Autowired
    public PurchaseInformationServiceImpl(PurchaseInformationRepository purchaseInformationRepository) {
        this.purchaseInformationRepository = purchaseInformationRepository;
    }
    @Override
    public List<PurchaseInformation> findAll() {
        return purchaseInformationRepository.findAll();
    }

    @Override
    public PurchaseInformation save(PurchaseInformation purchaseInformation) {
        return purchaseInformationRepository.save(purchaseInformation);
    }
}
