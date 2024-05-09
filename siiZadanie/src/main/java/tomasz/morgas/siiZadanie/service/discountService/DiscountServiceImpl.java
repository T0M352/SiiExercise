package tomasz.morgas.siiZadanie.service.discountService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tomasz.morgas.siiZadanie.entity.Discount;

import java.util.List;
import java.util.Optional;
@Repository
public class DiscountServiceImpl implements  DiscountService{
    DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }



    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Transactional
    @Override
    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public Discount getDetails(String promoCode) {
        Optional<Discount> result = discountRepository.findById(promoCode);
        Discount discount = null;

        if (result.isPresent()) {
            discount = result.get();
        }
        else {
            return null;
        }

        return discount;
    }
}
