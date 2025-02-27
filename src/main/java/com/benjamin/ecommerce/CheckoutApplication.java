package com.benjamin.ecommerce;

import com.benjamin.ecommerce.shared.core.payment.dto.CreatePaymentRequest;
import com.benjamin.ecommerce.shared.core.payment.models.PaymentMethod;
import com.benjamin.ecommerce.shared.core.purchase.events.PaymentRequestEvent;
import com.benjamin.ecommerce.shared.integration.EventBus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Map;


@SpringBootApplication
public class CheckoutApplication {

	//@Autowired
	EventBus eventBus;

	public static void main(String[] args) {
		SpringApplication.run(CheckoutApplication.class, args);
	}

	//@EventListener(ApplicationReadyEvent.class)
	public void onReady() {
    	this.eventBus.dispatch(new PaymentRequestEvent(new CreatePaymentRequest(PaymentMethod.CREDIT_CARD, Map.of("card", "111111"), 2000.0)));
	}
}
