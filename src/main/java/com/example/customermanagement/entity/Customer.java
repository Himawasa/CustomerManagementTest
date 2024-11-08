package com.example.customermanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * Customer エンティティクラス
 * このクラスは、顧客情報をデータベース上で管理するためのエンティティです。
 * 顧客テーブル（customers）と対応し、顧客ID、名前、ふりがな、メールアドレス、電話番号、住所の情報を持ちます。
 * Spring Data JPAを使用して、データベースとの連携を行います。
 */

@Entity  // エンティティクラスであることを示すアノテーション
@Table(name = "customers")  // データベースのテーブル名を明示的に指定
@Data   // Lombokを利用してゲッター・セッターを自動生成
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主キーの設定（自動生成）
    private Long id;  // 顧客ID

    private String name;  // 顧客の名前
    private String kana;  // ふりがな
    private String email; // メールアドレス
    private String phone; // 電話番号
    private String address; // 住所
}
