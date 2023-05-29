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
	private int playerId=0;

	public static void main(String[] args) {
		SpringApplication.run(SerwerApplication.class, args);
	}

	@GetMapping("/getplayerid")
	public int getPlayerId() {
		playerId++;
		if(playerId>=5) playerId=1;
		return playerId;
	}
}
