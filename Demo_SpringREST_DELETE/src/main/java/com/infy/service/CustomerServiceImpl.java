package com.infy.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.exception.InfyBankException;
import com.infy.repository.CartRepository;
import com.infy.repository.UserRepository;
import com.infy.repository.productRepository;
import com.infy.entity.Cart;
import com.infy.entity.Product;
import com.infy.entity.User;
import com.infy.dto.CartDTO;
import com.infy.dto.UserDTO;
import com.infy.dto.productDTO;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private productRepository productrepo;
	@Autowired
	private CartRepository cartRepository;
	

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

	@Override
	public void makeCart(CartDTO cartDTO) throws InfyBankException {
		Cart cart=new Cart();
		cart.setProductName(cartDTO.getProductName());
		cart.setPrice(cartDTO.getPrice());
		cartRepository.save(cart);
	}

	@Override
	public List<CartDTO> getCart() throws InfyBankException {
		Iterable<Cart> cartItems = cartRepository.findAll();
		List<CartDTO> cartDTOs= new ArrayList<>();
		cartItems.forEach((obj) -> {
			CartDTO cartDTO=new CartDTO();
			cartDTO.setProductName(obj.getProductName());
			cartDTO.setPrice(obj.getPrice());
			cartDTOs.add(cartDTO);
		});
		return cartDTOs;
	}

	@Override
	public void updateCartPrice(String productName, String price) throws InfyBankException {
		Optional<Cart> car = cartRepository.findById(productName);
		Cart c = car.orElseThrow(() -> new InfyBankException("Service.USER_NOT_FOUND"));
		c.setPrice(price);
	}

	@Override
	public void addProduct(productDTO dto) throws InfyBankException {
		Product product=new Product();
		product.setSellerName(dto.getSellerName());
		product.setProductName(dto.getProductName());
		product.setProductPrice(dto.getProductPrice());
		productrepo.save(product);
	}

	@Override
	public void deleteProduct(String seller) throws InfyBankException {
		Optional<Product> product = productrepo.findById(seller);
		product.orElseThrow(() -> new InfyBankException("Service.USER_NOT_FOUND"));
		productrepo.deleteById(seller);
		
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}
}
