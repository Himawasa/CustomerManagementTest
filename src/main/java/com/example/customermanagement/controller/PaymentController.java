package com.example.customermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.customermanagement.entity.Invoice;
import com.example.customermanagement.entity.Payment;
import com.example.customermanagement.repository.InvoiceRepository;
import com.example.customermanagement.repository.PaymentRepository;

/**
 * Payment コントローラー
 * 入金情報の一覧表示、作成、編集、削除の処理を行います。
 */
@Controller
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * 入金一覧を表示する処理
     */
    @GetMapping("/payments")
    public String listPayments(Model model) {
        List<Payment> payments = paymentRepository.findAll();
        model.addAttribute("payments", payments);
        return "payments/list";
    }

    /**
     * 入金作成フォームを表示する処理
     */
    @GetMapping("/payments/new")
    public String showCreateForm(Model model) {
        List<Invoice> invoices = invoiceRepository.findAll();
        model.addAttribute("invoices", invoices);
        model.addAttribute("payment", new Payment());
        return "payments/form";
    }

    /**
     * 入金を保存する処理
     */
    @PostMapping("/payments/save")
    public String savePayment(Payment payment) {
        paymentRepository.save(payment);
        return "redirect:/payments";
    }

    /**
     * 入金を削除する処理
     */
    @GetMapping("/payments/delete")
    public String deletePayment(@RequestParam("id") Long id) {
        paymentRepository.deleteById(id);
        return "redirect:/payments";
    }
}
