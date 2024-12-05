package com.peloteros.app.peloteros.controller;

import com.peloteros.app.peloteros.entity.PaymentIntentDTO;
import com.peloteros.app.peloteros.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost/5500"})
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Map<String, String> createPaymentIntent(@RequestBody PaymentIntentDTO paymentRequest) {
        try {
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(
                paymentRequest.getAmount(),
                paymentRequest.getCurrency(),
                paymentRequest.getDescription()
            );
            // Retorna un mapa con solo el client_secret
            return Map.of("clientSecret", paymentIntent.getClientSecret());
        } catch (StripeException e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }


    
 // Confirmar el pago
    @PostMapping("/confirmpayment")
    public String confirmPayment(@RequestParam String paymentIntentId, @RequestParam String paymentMethodId) {
        try {
            // Llamar al método para confirmar el pago
            String returnUrl = "https://www.example.com"; // URL de retorno después de la confirmación
            PaymentIntent confirmedPayment = paymentService.confirmPaymentIntent(paymentIntentId, paymentMethodId, returnUrl);
            System.out.println("Pago confirmado con estado: " + confirmedPayment.getStatus());
            return "payment_success"; // Página de éxito
        } catch (StripeException e) {
            e.printStackTrace();
            return "payment_error"; // Página de error
        }
    }

    // Cancelar el pago
    @PostMapping("/cancelpayment")
    public String cancelPayment(@RequestParam String paymentIntentId) {
        try {
            // Llamar al método para cancelar el pago
            PaymentIntent cancelledPayment = paymentService.cancelPaymentIntent(paymentIntentId);
            System.out.println("Pago cancelado con estado: " + cancelledPayment.getStatus());
            return "payment_cancelled"; // Página de cancelación
        } catch (StripeException e) {
            e.printStackTrace();
            return "payment_error"; // Página de error
        }
    }
}
