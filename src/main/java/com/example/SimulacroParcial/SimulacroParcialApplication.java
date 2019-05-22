package com.example.SimulacroParcial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulacroParcialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulacroParcialApplication.class, args);

		/*Para poner en el PostMan
		* {
			"name":"Pepe",
			"surname":"Juarez",
			"politicalParty":"UDP"
		  }
		  {
			"votesdate":null,
			"candidate":null

		  }*/
	}

}
