package tomasz.morgas.siiZadanie.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomasz.morgas.siiZadanie.exceptions.ProductAlreadyExistException;
import tomasz.morgas.siiZadanie.exceptions.ProductErrorResponse;
import tomasz.morgas.siiZadanie.exceptions.ProductNotFoundException;
import tomasz.morgas.siiZadanie.service.ProductService;
import tomasz.morgas.siiZadanie.entity.Product;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //get na pobranie produktów
    @GetMapping("/getAllProducts")
    public List<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/getProduct/{name}") //TODO testowo do usuniecia
    public Product getProduct(@PathVariable String name){
        Product product = productService.findByName(name);
        if(product == null){
            throw new ProductNotFoundException("Produkt o nazwie: " + name + " nie istnieje");
        }
        return product;
    }


    //post na stworzenie produktu
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        Product productToFind = productService.findByName(product.getName());
        if(productToFind != null){
            throw new ProductAlreadyExistException("Produkt o nazwie: " + product.getName() + " już istnieje");
        }
        Product dbProduct = productService.save(product);
        return dbProduct;

    }


    //aktualizacja stanu produktu
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        Product productToFind = productService.findByName(product.getName());
        if(productToFind == null){
            throw new ProductNotFoundException("Produkt o nazwie: " + product.getName() + " nie istnieje");
        }
        Product dbProduct = productService.save(product);
        return dbProduct;
    }


    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc){
        ProductErrorResponse error = new ProductErrorResponse();
        error.setStatus(404);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductAlreadyExistException exc){
        ProductErrorResponse error = new ProductErrorResponse();
        error.setStatus(409);
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception exc){
        ProductErrorResponse error = new ProductErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
