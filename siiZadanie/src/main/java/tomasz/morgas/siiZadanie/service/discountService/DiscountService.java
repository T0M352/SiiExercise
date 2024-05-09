package tomasz.morgas.siiZadanie.service.discountService;

import tomasz.morgas.siiZadanie.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> findAll();

    Discount createDiscount(Discount discount);

    Discount getDetails(String promoCode);
}
