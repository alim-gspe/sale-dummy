package com.gspe.sale;

import com.gspe.sale.dto.NpwpDto;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/customers")
    ResponseEntity<?> customers() {
        val result = mainService.customers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customers/{id}")
    ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        val result = mainService.findCustomerById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/purchase-orders/{customerId}")
    ResponseEntity<?> purchaseOrders(@PathVariable Long customerId) {
        val result = mainService.purchaseOrders(customerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/delivery-orders/{customerId}")
    ResponseEntity<?> deliveryOrders(@PathVariable Long customerId) {
        val result = mainService.deliveryOrders(customerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bill-to/{customerId}")
    ResponseEntity<?> billToList(@PathVariable Long customerId) {
        val result = mainService.dropDownList(customerId, "bill");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bill-contact/{customerId}")
    ResponseEntity<?> billContactList(@PathVariable Long customerId) {
        val result = mainService.dropDownList(customerId, "contact");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/npwps")
    ResponseEntity<?> listNpwp() {
        val result = mainService.listNpwp();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/npwps/{id}")
    ResponseEntity<?> updateNpwp(@PathVariable Long id, @RequestBody NpwpDto npwpDto) {
        mainService.updateNpwp(npwpDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/npwps/{id}")
    ResponseEntity<?> npwpById(@PathVariable Long id) {
        val result = mainService.npwpById(id);
        return ResponseEntity.ok(result);
    }
}
