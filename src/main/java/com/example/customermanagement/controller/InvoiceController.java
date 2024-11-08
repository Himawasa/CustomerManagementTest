package com.example.customermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.customermanagement.entity.Contract;
import com.example.customermanagement.entity.Invoice;
import com.example.customermanagement.repository.ContractRepository;
import com.example.customermanagement.repository.InvoiceRepository;

/**
 * Invoice コントローラー
 * 請求情報の一覧表示、作成、編集、削除の処理を行います。
 */
@Controller
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ContractRepository contractRepository;

    /**
     * 請求一覧を表示する処理
     */
    @GetMapping("/invoices")
    public String listInvoices(Model model) {
        List<Invoice> invoices = invoiceRepository.findAll();
        model.addAttribute("invoices", invoices);
        return "invoices/list";
    }

    /**
     * 請求作成フォームを表示する処理
     */
    @GetMapping("/invoices/new")
    public String showCreateForm(Model model) {
        List<Contract> contracts = contractRepository.findAll();
        model.addAttribute("contracts", contracts);
        model.addAttribute("invoice", new Invoice());
        return "invoices/form";
    }

    /**
     * 請求を保存する処理
     */
    @PostMapping("/invoices/save")
    public String saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
        return "redirect:/invoices";
    }

    /**
     * 請求を削除する処理
     */
    @GetMapping("/invoices/delete")
    public String deleteInvoice(@RequestParam("id") Long id) {
        invoiceRepository.deleteById(id);
        return "redirect:/invoices";
    }
}
