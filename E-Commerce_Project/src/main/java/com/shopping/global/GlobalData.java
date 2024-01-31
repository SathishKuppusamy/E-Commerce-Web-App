package com.shopping.global;

import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Product;

public class GlobalData {

	public static List<Product> cart;
	
	static {
		cart = new ArrayList<Product>(); 
		
	}
}
