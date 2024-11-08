package com.example.customermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.repository.CustomerRepository;

/**
 * 顧客情報を管理するコントローラクラス
 * 顧客の一覧表示、詳細表示、編集、削除機能を提供する。
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 顧客一覧を表示するためのメソッド
     * @param model モデルオブジェクト
     * @return 顧客一覧ビュー名
     */
    @GetMapping("/customers")
    public String listCustomers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Customer> customers;
        if (keyword != null && !keyword.isEmpty()) {
            customers = customerRepository.findByNameContainingOrEmailContaining(keyword, keyword);
        } else {
            customers = customerRepository.findAll();
        }
        model.addAttribute("customers", customers);
        return "customer_list";
    }

    @GetMapping("/customers/detail")
    public String showCustomerDetail(@RequestParam("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return "redirect:/customers?error=notfound";
        }
        model.addAttribute("customer", customer);
        return "customers/detail";
    }

    @GetMapping("/customers/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return "redirect:/customers?error=notfound";
        }
        model.addAttribute("customer", customer);
        return "customers/form"; // 顧客編集ページ
    }

    @PostMapping("/customers/save")
    public String saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete")
    public String deleteCustomer(@RequestParam("id") Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }
}
