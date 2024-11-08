package com.example.customermanagement.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // ログインページの表示
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html へ
    }

    // ログインの処理
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, HttpSession session, Model model) {
        // 固定のID "user" でログインチェック
        if ("user".equals(username)) {
            session.setAttribute("loggedInUser", username); // セッションにユーザー情報を保存
            return "redirect:/customers"; // 顧客一覧ページへリダイレクト
        } else {
            model.addAttribute("error", "IDが正しくありません。"); // エラーメッセージを表示
            return "login"; // ログインページに戻る
        }
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // セッションを無効化
        return "redirect:/login?logout=true"; // ログアウト後にログインページへリダイレクト
    }
}
