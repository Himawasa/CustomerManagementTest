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
 * 契約の一覧表示、詳細表示、作成、編集、削除の処理を行います。
 */
@Controller
public class ContractController {

    @Autowired
    private ContractRepository contractRepository; // Contractエンティティ用のリポジトリ

    @Autowired
    private CustomerRepository customerRepository; // Customerエンティティ用のリポジトリ

    /**
     * 契約一覧を表示する処理
     * @param model モデルオブジェクト
     * @return 契約一覧ページのテンプレート
     */
    @GetMapping("/contracts")
    public String listContracts(Model model) {
        List<Contract> contracts = contractRepository.findAll(); // 全契約を取得
        model.addAttribute("contracts", contracts); // モデルに契約リストを追加
        return "contracts/list"; // 契約一覧ページ（contracts/list.html）を表示
    }

    /**
     * 契約詳細を表示する処理
     * @param id 契約ID
     * @param model モデルオブジェクト
     * @return 契約詳細ページのテンプレート
     */
    @GetMapping("/contracts/detail")
    public String showContractDetail(@RequestParam("id") Long id, Model model) {
        Contract contract = contractRepository.findById(id).orElse(null); // 契約をIDで検索
        if (contract == null) {
            return "redirect:/contracts?error=notfound"; // 見つからない場合は一覧にリダイレクト
        }
        model.addAttribute("contract", contract); // モデルに契約詳細を追加
        return "contracts/detail"; // 契約詳細ページ（contracts/detail.html）を表示
    }

    /**
     * 契約作成フォームを表示する処理
     * @param model モデルオブジェクト
     * @return 契約作成・編集ページのテンプレート
     */
    @GetMapping("/contracts/new")
    public String showCreateForm(Model model) {
        List<Customer> customers = customerRepository.findAll(); // 全顧客を取得
        model.addAttribute("customers", customers); // モデルに顧客リストを追加
        model.addAttribute("contract", new Contract()); // 新しい契約オブジェクトをモデルに追加
        return "contracts/form"; // 契約作成・編集ページ（contracts/form.html）を表示
    }

    /**
     * 契約編集フォームを表示する処理
     * @param id 編集対象の契約ID
     * @param model モデルオブジェクト
     * @return 契約編集ページのテンプレート
     */
    @GetMapping("/contracts/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Contract contract = contractRepository.findById(id).orElse(null); // 契約をIDで検索
        if (contract == null) {
            return "redirect:/contracts?error=notfound"; // 見つからない場合は一覧にリダイレクト
        }
        List<Customer> customers = customerRepository.findAll(); // 全顧客を取得
        model.addAttribute("customers", customers); // モデルに顧客リストを追加
        model.addAttribute("contract", contract); // 編集対象の契約をモデルに追加
        return "contracts/form"; // 契約編集ページ（contracts/form.html）を表示
    }

    /**
     * 契約を保存する処理（新規作成・編集共通）
     * @param contract 保存する契約オブジェクト
     * @return 契約一覧ページへのリダイレクト
     */
    @PostMapping("/contracts/save")
    public String saveContract(Contract contract) {
        contractRepository.save(contract); // 契約をデータベースに保存
        return "redirect:/contracts"; // 保存後、契約一覧ページにリダイレクト
    }

    /**
     * 契約を削除する処理
     * @param id 削除する契約ID
     * @return 契約一覧ページへのリダイレクト
     */
    @GetMapping("/contracts/delete")
    public String deleteContract(@RequestParam("id") Long id) {
        contractRepository.deleteById(id); // 指定されたIDの契約を削除
        return "redirect:/contracts"; // 削除後、契約一覧ページにリダイレクト
    }
}
