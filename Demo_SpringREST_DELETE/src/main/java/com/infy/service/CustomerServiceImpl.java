package com.infy.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import com.infy.repository.UserRepository;
import com.infy.repository.productRepository;
import com.infy.entity.Customer;
import com.infy.entity.Product;
import com.infy.entity.User;
import com.infy.dto.CustomerDTO;
import com.infy.dto.UserDTO;
import com.infy.dto.productDTO;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private productRepository productrepo;

	@Override
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setCustomerId(customer.getCustomerId());
		customer2.setDateOfBirth(customer.getDateOfBirth());
		customer2.setEmailId(customer.getEmailId());
		customer2.setName(customer.getName());
		return customer2;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() throws InfyBankException {
		Iterable<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customers2 = new ArrayList<>();
		customers.forEach(customer -> {
			CustomerDTO cust = new CustomerDTO();
			cust.setCustomerId(customer.getCustomerId());
			cust.setDateOfBirth(customer.getDateOfBirth());
			cust.setEmailId(customer.getEmailId());
			cust.setName(customer.getName());
			customers2.add(cust);
		});
		if (customers2.isEmpty())
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		return customers2;
	}

	@Override
	public Integer addCustomer(CustomerDTO customer) throws InfyBankException {
		Customer customerEntity = new Customer();
		customerEntity.setDateOfBirth(customer.getDateOfBirth());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setName(customer.getName());
		customerEntity.setCustomerId(customer.getCustomerId());
		Customer customerEntity2 = customerRepository.save(customerEntity);
		return customerEntity2.getCustomerId();
	}

	@Override
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		Customer c = customer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		c.setEmailId(emailId);
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		customer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customerRepository.deleteById(customerId);
	}

	@Override
	public void registerUser(UserDTO userDTO) throws InfyBankException {
		User user=new User();
		user.setFullname(userDTO.getFullname());
		user.setEmailId(userDTO.getEmailId());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setUserType(userDTO.getUserType());
		userRepository.save(user);
	}

	@Override
	public String loginUser(String username) throws InfyBankException {
		Optional<User> optional=userRepository.findById(username);
		User user=optional.orElseThrow(()->new InfyBankException("UserName not found"));
		return user.getPassword();
	}

	@Override
	public String getUserType(String username) throws InfyBankException {
		Optional<User> optional=userRepository.findById(username);
		User user=optional.orElseThrow(()->new InfyBankException("UserName not found"));
		return user.getUserType();
	}

	@Override
	public List<productDTO> getCatalogue() throws InfyBankException {
		Iterable<Product> products = productrepo.findAll();
		List<productDTO> dtos=new ArrayList<>();
		products.forEach((obj) -> {
			productDTO dto=new productDTO();
			dto.setProductName(obj.getProductName());
			dto.setProductPrice(obj.getProductPrice());
			dto.setSellerName(obj.getSellerName());
			dtos.add(dto);
		});
		return dtos;
	}

}
