package de.hsansbach.ecommerce.process.bean;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberProcessBean {

	public int execute() {
		int randomNumber = ThreadLocalRandom.current().nextInt(100);

		return randomNumber;
	}

}
