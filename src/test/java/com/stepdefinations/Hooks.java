package com.stepdefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@GetCart and @@AddItem ")
	public void createCart() {
		
	}
}
