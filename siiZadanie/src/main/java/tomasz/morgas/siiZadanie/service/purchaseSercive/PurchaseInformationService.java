package tomasz.morgas.siiZadanie.service.purchaseSercive;

import tomasz.morgas.siiZadanie.entity.PurchaseInformation;

import java.util.List;

public interface PurchaseInformationService {
    List<PurchaseInformation> findAll();

    PurchaseInformation save(PurchaseInformation purchaseInformation);
}
