package com.peloteros.app.peloteros.service;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCancelParams;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
@Service
public class PaymentService {
	
	public PaymentIntent createPaymentIntent(long amount, String currency, String description) throws StripeException {
	    long amountInCents = Math.round(amount * 100);

	    Stripe.apiKey = "sk_test_51NczKhIflwBxpIOlCi2nSR13Jl4wwamkQPxc7LgBp1UDquGxz4nFR3FYT8dchPhcvl2Bc67EzT4WGGhGj2EMQS6q00gOYxBkpG";
	    
	    PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
	            .setAmount(amountInCents)
	            .setCurrency(currency)
	            .setDescription(description)
	            .setAutomaticPaymentMethods(
	                    PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
	                            .setEnabled(true)
	                            .build()
	            )
	            .build();

	    PaymentIntent paymentIntent = PaymentIntent.create(params);
	    
	    // Log para revisar el PaymentIntent y el clientSecret
	    System.out.println("PaymentIntent creado: " + paymentIntent.toJson());

	    return paymentIntent;
	}

	
	public PaymentIntent confirmPaymentIntent(String paymentIntentId, String paymentMethodId, String returnUrl) throws StripeException {
        // Recuperamos el PaymentIntent
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        // Crear los parámetros de confirmación
        PaymentIntentConfirmParams params = PaymentIntentConfirmParams.builder()
                .setPaymentMethod(paymentMethodId)  // ID del PaymentMethod (por ejemplo, "pm_card_visa")
                .setReturnUrl(returnUrl)           // URL de retorno después de confirmar el pago
                .build();

        // Confirmamos el PaymentIntent
        return paymentIntent.confirm(params);
    }
	
	public PaymentIntent cancelPaymentIntent(String paymentIntentId) throws StripeException {
        // Recuperamos el PaymentIntent
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        // Crear los parámetros de cancelación (aunque no se requieren parámetros específicos, Stripe los permite como un objeto vacío)
        PaymentIntentCancelParams params = PaymentIntentCancelParams.builder().build();

        // Cancelamos el PaymentIntent
        return paymentIntent.cancel(params);
    }
	
	
}
