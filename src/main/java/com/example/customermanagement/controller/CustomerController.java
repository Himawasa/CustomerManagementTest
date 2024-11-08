package com.example.customermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.customermanagement.repository.CustomerRepository;

/**
 * 顧客情報を管理するコントローラクラス
 * 顧客の一覧表示機能を提供する。
 */
@Controller
public class CustomerController {

    // Customerリポジトリを注入
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 顧客一覧を表示するためのメソッド
     * SQLで表現すると：SELECT * FROM customers;
     */
    @GetMapping("/customers")
    public String listCustomers(Model model) {
        // 顧客一覧を取得し、モデルに追加
        model.addAttribute("customers", customerRepository.findAll());
        return "customer_list";  // 顧客一覧を表示するビュー名
    }
}
