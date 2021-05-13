package com.infy.service;

import java.util.List;

import com.infy.dto.CartDTO;
import com.infy.dto.UserDTO;
import com.infy.dto.productDTO;
import com.infy.exception.InfyBankException;

public interface CustomerService {
	public void registerUser(UserDTO userDTO) throws InfyBankException;
	public String loginUser(String username) throws InfyBankException;
	public String getUserType(String username) throws InfyBankException;
	public List<productDTO> getCatalogue() throws InfyBankException;
	public void makeCart(CartDTO cartDTO) throws InfyBankException;
	public List<CartDTO> getCart() throws InfyBankException;
	public void updateCartPrice(String productName,String price) throws InfyBankException;
	public void addProduct(productDTO dto) throws InfyBankException;
	public void deleteProduct(String seller) throws InfyBankException;
	public void test();
}
