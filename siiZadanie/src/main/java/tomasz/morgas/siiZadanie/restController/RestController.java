package tomasz.morgas.siiZadanie.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomasz.morgas.siiZadanie.entity.*;
import tomasz.morgas.siiZadanie.exceptions.*;
import tomasz.morgas.siiZadanie.service.purchaseSercive.PurchaseInformationService;
import tomasz.morgas.siiZadanie.service.discountService.DiscountService;
import tomasz.morgas.siiZadanie.service.productService.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private ProductService productService;
    private DiscountService discountService;
    private PurchaseInformationService purchaseInformationService;
    @Autowired
    public RestController(ProductService productService, DiscountService discountService, PurchaseInformationService purchaseInformationService) {
        this.productService = productService;
        this.discountService = discountService;
        this.purchaseInformationService = purchaseInformationService;
    }



    //1. Create a new product
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        Product productToFind = productService.findByName(product.getName());
        if(productToFind != null){
            throw new AlreadyExistException("Produkt o nazwie: " + product.getName() + " już istnieje");
        }
        Product dbProduct = productService.save(product);
        return dbProduct;

    }

    //2. Get all products
    @GetMapping("/getAllProducts")
    public List<Product> getProducts(){
        return productService.findAll();
    }



    //3. Update product data
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        Product productToFind = productService.findByName(product.getName());
        if(productToFind == null){
            throw new NotFoundException("Produkt o nazwie: " + product.getName() + " nie istnieje");
        }
        Product dbProduct = productService.save(product);
        return dbProduct;
    }

    //4. Create a new promo code.
    @PostMapping("/createPromocode")
    public Discount createPromocode(@RequestBody Discount discount){
        Discount discountToFind = discountService.getDetails(discount.getPromoCode());
        if(discountToFind != null){
            throw new AlreadyExistException("Ten promokod juz istnieje");
        }
        String promoCode = discount.getPromoCode();
        if(promoCode.length() < 3 || promoCode.length() > 24 || containsWhitespaces(promoCode)){ //TODO tutaj powinien byc bindingResult
            throw new WrongPromocodeException("Promokod powinien miec od 3 do 24 znaków i nie posiadać znaków białych");
        }
        Discount dbDiscount = discountService.createDiscount(discount);
        return dbDiscount;
    }

    //5. Get all promo codes.
    @GetMapping("/getAllCodes")
    public List<String> getAllCodes(){
        return discountService.findAll().stream()
                .map(Discount::getPromoCode)
                .collect(Collectors.toList());
    }

    //6. Get one promo code's details by providing the promo code. The detail should also contain the number of usages.
    @GetMapping("/getPromocodeDetails/{code}")
    public Discount getPromocodeDetails(@PathVariable String code){
        Discount discount = discountService.getDetails(code);
        if(discount == null){
            throw new NotFoundException("Ten kod nie istnieje");
        }
        return discount;
    }

    //7. Get the discount price by providing a product and a promo code.
    @PostMapping("/calculateDiscount")
    public DiscountResponse calculateDiscount(@RequestBody DiscountRequest discountRequest){
        Discount discount = discountService.getDetails(discountRequest.getPromoCode());
        Product product = productService.findByName(discountRequest.getProductName());
        double discountPrice;
        if(discount == null){
            throw new NotFoundException("Ten promokod nie istnieje");
        }
        if(product == null){
            throw new NotFoundException("Ten produkt nie istnieje");
        }
        if(discount.getExpirationDate().isBefore(LocalDate.now())){
            return new DiscountResponse("Data waznosci uplynela", product.getRegularPrice());
        }
        if(!discount.getCurrency().equals(product.getCurrency())){
            return new DiscountResponse("Waluty sie nie zgadzaja", product.getRegularPrice());
        }
        if(discount.getMaxUsages() <= discount.getCurrentUsages()){
            return new DiscountResponse("Przekroczono ilość użyć", product.getRegularPrice());
        }
        discountPrice = product.getRegularPrice() - discount.getDiscountAmount();
        if(discountPrice < 0)
            discountPrice = 0;


        return new DiscountResponse("Promocja moze zostac zastosowana", discountPrice);
    }

    //8. Simulate purchase
    @PostMapping("/purchase")
    public String purchaseProduct(@RequestBody DiscountRequest discountRequest){


        Discount discount = discountService.getDetails(discountRequest.getPromoCode());
        Product product = productService.findByName(discountRequest.getProductName());
        if(product == null){
            throw new NotFoundException("Ten produkt nie istnieje");
        }
        if(discountRequest.getPromoCode() != null){
            if(discount == null){
                throw new NotFoundException("Ten promokod nie istnieje");
            }
            if(discount.getExpirationDate().isBefore(LocalDate.now())){
                return "Data waznosci uplynela";
            }
            if(!discount.getCurrency().equals(product.getCurrency())){
                return "Waluty sie nie zgadzaja";
            }
            if(discount.getMaxUsages() <= discount.getCurrentUsages()){
                return "Przekroczono ilość użyć";
            }
        }
        PurchaseInformation purchaseInformation = new PurchaseInformation(LocalDate.now(), product.getRegularPrice(), 0, product.getName(), product.getCurrency());

        double discountPrice = product.getRegularPrice();
        if(discount!=null){
            discountPrice = product.getRegularPrice() - discount.getDiscountAmount();
            discount.setCurrentUsages(discount.getCurrentUsages()+1);
            discountService.createDiscount(discount);
            if(discountPrice < 0)
                discountPrice = 0;
            purchaseInformation.setDiscountAmount(product.getRegularPrice() - discountPrice);
            purchaseInformationService.save(purchaseInformation);
        }

        return "Produkt zakupiony prawidłowo";
    }

    //9. [Optional] A sales report: number of purchases and total value by currency (see below)
    @GetMapping("/salesRaport")
    private List<SalesRaport> salesRaport(){
        List<PurchaseInformation> list = purchaseInformationService.findAll();
        Map<String, Double> totalAmountByCurrency = new HashMap<>();
        Map<String, Double> totalDiscountByCurrency = new HashMap<>();
        Map<String, Integer> purchasesCountByCurrency = new HashMap<>();


        for (PurchaseInformation purchase : list) {
            String currency = purchase.getCurrency();
            double regularPrice = purchase.getRegularPrice();
            double discountAmount = purchase.getDiscountAmount();

            totalAmountByCurrency.put(currency, totalAmountByCurrency.getOrDefault(currency, 0.0) + regularPrice);
            totalDiscountByCurrency.put(currency, totalDiscountByCurrency.getOrDefault(currency, 0.0) + discountAmount);
            purchasesCountByCurrency.put(currency, purchasesCountByCurrency.getOrDefault(currency, 0) + 1);
        }
        List<SalesRaport> salesReports = new ArrayList<>();
        for (Map.Entry<String, Double> entry : totalAmountByCurrency.entrySet()) {
            String currency = entry.getKey();
            double totalAmount = entry.getValue();
            double totalDiscount = totalDiscountByCurrency.get(currency);
            int purchasesCount = purchasesCountByCurrency.get(currency);

            SalesRaport salesRaport = new SalesRaport(currency, totalAmount, totalDiscount, purchasesCount);
            salesReports.add(salesRaport);
        }

        return salesReports;
    }



    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(404);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AlreadyExistException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(409);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(WrongPromocodeException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private boolean containsWhitespaces(String str){
        for (char ch : str.toCharArray()){
            if(Character.isWhitespace(ch))
                return true;
        }
        return false;
    }


}
