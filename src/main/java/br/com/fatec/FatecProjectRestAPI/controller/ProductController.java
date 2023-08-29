package br.com.fatec.FatecProjectRestAPI.controller;

import br.com.fatec.FatecProjectRestAPI.entity.Product;
import br.com.fatec.FatecProjectRestAPI.exception.ResponseGenericException;
import br.com.fatec.FatecProjectRestAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/list")
    public ResponseEntity<Object> list(){
        List<Product> result = productService.getProducts();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findProduct/{idProduct}")
    public ResponseEntity<Object> find(@PathVariable Long idProduct){
        Optional<Product> result = productService.findProductById(idProduct);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> save(@RequestBody Product product){
        Product result = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idProduct}")
    public ResponseEntity<Object> delete(@PathVariable Long idProduct){
        HashMap<String, Object> result = productService.deleteProduct(idProduct);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
}
