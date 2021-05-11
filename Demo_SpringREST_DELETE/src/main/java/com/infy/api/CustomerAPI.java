package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.CartDTO;
import com.infy.dto.CustomerDTO;
import com.infy.dto.UserDTO;
import com.infy.dto.productDTO;
import com.infy.exception.InfyBankException;
import com.infy.service.CustomerService;

@RestController
@RequestMapping(value = "/infybank")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Environment environment;

	@GetMapping(value = "/customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws InfyBankException {
		List<CustomerDTO> customerList = customerService.getAllCustomers();
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/user")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) throws InfyBankException{
		customerService.registerUser(userDTO);
		String successMessage = "Successfully Registered";
		return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/cart")
	public ResponseEntity<String> makeCart(@RequestBody CartDTO cartDTO) throws InfyBankException{
		customerService.makeCart(cartDTO);
		String successMessage = "sucessfully made";
		return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/carts")
	public ResponseEntity<List<CartDTO>> getCartItems() throws InfyBankException{
		List<CartDTO> list=customerService.getCart();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/user/{username}")
	public ResponseEntity<String> loginUser(@PathVariable String username) throws InfyBankException{
		String password=customerService.loginUser(username);
		return new ResponseEntity<>(password,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/product")
	public ResponseEntity<String> addProduct(@RequestBody productDTO dto) throws InfyBankException{
		customerService.addProduct(dto);
		String successMessage = "Product added";
		return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/products")
	public ResponseEntity<List<productDTO>> getCatalogue() throws InfyBankException{
		List<productDTO> list=customerService.getCatalogue();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/{username}")
	public ResponseEntity<String> getUserType(@PathVariable String username) throws InfyBankException{
		String userType=customerService.getUserType(username);
		return new ResponseEntity<>(userType,HttpStatus.OK);
	}
	@PutMapping(value = "/customers/{customerId}")
	public ResponseEntity<String> updateCustomer(@PathVariable Integer customerId, @RequestBody CustomerDTO customer)
			throws InfyBankException {
		customerService.updateCustomer(customerId, customer.getEmailId());
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
    
	@DeleteMapping(value = "/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) throws InfyBankException {
		customerService.deleteCustomer(customerId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/product/{sellerName}")
	public ResponseEntity<String> deleteProduct(@PathVariable String sellerName) throws InfyBankException{
		customerService.deleteProduct(sellerName);
		String successMessage = "Deleted Successfully";
		return new ResponseEntity<>(successMessage,HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/cart/{productName}")
	public ResponseEntity<String> updateCartPrice(@PathVariable String productName,@RequestBody CartDTO cartDTO) throws InfyBankException{
			customerService.updateCartPrice(productName,cartDTO.getPrice());
			String successMessage = "Successfully Updated";
			return new ResponseEntity<>(successMessage,HttpStatus.OK);
}
}
