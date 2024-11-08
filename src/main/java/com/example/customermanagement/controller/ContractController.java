package com.example.customermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.customermanagement.entity.Contract;
import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.repository.ContractRepository;
import com.example.customermanagement.repository.CustomerRepository;

/**
 * Contract コントローラー
 * 契約の一覧表示、作成、更新、削除の処理を行います。
 */
@Controller
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // 契約一覧を表示
    @GetMapping("/contracts")
    public String listContracts(Model model) {
        List<Contract> contracts = contractRepository.findAll();
        model.addAttribute("contracts", contracts);
        return "contracts/list";  // 契約一覧ページ（contracts/list.html）を表示
    }

    // 契約作成フォームを表示
    @GetMapping("/contracts/new")
    public String showCreateForm(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("contract", new Contract());
        return "contracts/form";  // 契約作成・編集ページ（contracts/form.html）を表示
    }

    // 契約を保存
    @PostMapping("/contracts/save")
    public String saveContract(Contract contract) {
        contractRepository.save(contract);
        return "redirect:/contracts";  // 保存後、契約一覧ページにリダイレクト
    }

    // 契約を削除
    @GetMapping("/contracts/delete")
    public String deleteContract(@RequestParam("id") Long id) {
        contractRepository.deleteById(id);
        return "redirect:/contracts";  // 削除後、契約一覧ページにリダイレクト
    }
}
