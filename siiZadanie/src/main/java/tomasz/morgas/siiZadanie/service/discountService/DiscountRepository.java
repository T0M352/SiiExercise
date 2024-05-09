package tomasz.morgas.siiZadanie.service.discountService;

import org.springframework.data.jpa.repository.JpaRepository;
import tomasz.morgas.siiZadanie.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, String> {
}
