package br.com.fatec.FatecProjectRestAPI.service;

import br.com.fatec.FatecProjectRestAPI.entity.Customer;
import br.com.fatec.FatecProjectRestAPI.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getInfoCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        if(validateCustomer(customer)){
            return customerRepository.saveAndFlush(customer);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A renda mensal é obrigatoria e deve ser maior que 0(zero)!");
        }
    }

    public HashMap<String, Object> deleteCustomer(Long idCustomer){
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(idCustomer).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")));
        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "Cliente: " + customer.get().getFirstNameCustomer() + " " + customer.get().getLastNameCustomer() + " excluido com sucesso");
        return result;
    }

    public Boolean validateCustomer(Customer customer){
        if(customer.getMonthlyIncomeCustomer() != null &&
        customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) >= 0 &&
        customer.getPasswordCustomer() != null &&
        !customer.getPasswordCustomer().equals("")) {
            return true;
        }else{
            return false;
        }
    }
}
