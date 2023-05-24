package com.example.serwer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class SerwerApplication {
	private int turn=1;

	public static void main(String[] args) {
		SpringApplication.run(SerwerApplication.class, args);
	}


	@GetMapping("/getturn")
	public int turn() {
		return turn;
	}

	@GetMapping("/endturn")
	public void endturn() {
		turn++;
		if(turn==5) turn=1;
	}

}
