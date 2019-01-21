package com.example.algamoney.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneradorSenha {
		public static void main(String[] args)
		{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			System.out.println(encoder.encode("admin"));
			
			//$2a$10$OCGwFScAeVRIwTHRRLkzOuq0yK1hugrJ.J9JmMvl1hjKOgHZPG.3m
		}
}
