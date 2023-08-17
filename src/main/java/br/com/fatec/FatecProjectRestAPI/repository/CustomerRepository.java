package br.com.fatec.FatecProjectRestAPI.repository;

import br.com.fatec.FatecProjectRestAPI.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//repository Customer
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
