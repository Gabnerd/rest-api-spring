package br.com.fatec.FatecProjectRestAPI.controller;

import br.com.fatec.FatecProjectRestAPI.entity.Customer;
import br.com.fatec.FatecProjectRestAPI.exception.ResponseGenericException;
import br.com.fatec.FatecProjectRestAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/list")
    public ResponseEntity<Object> list(){
        List<Customer> result = customerService.getInfoCustomers();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findCustomer/{idCustomer}")
    public ResponseEntity<Object> find(@PathVariable Long idCustomer){
        Optional<Customer> result = customerService.findCustomerById(idCustomer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> save(@RequestBody Customer customer){
        Customer result = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value="/delete/{idCustomer}")
    public ResponseEntity<Object> delete(@PathVariable Long idCustomer){
        HashMap<String, Object> result = customerService.deleteCustomer(idCustomer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Customer customer){
        Customer result = customerService.updateCustomer(customer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

}
