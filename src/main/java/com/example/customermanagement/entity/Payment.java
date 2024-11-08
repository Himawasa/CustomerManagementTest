package com.example.customermanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

/**
 * Payment エンティティクラス
 * このクラスは、入金情報をデータベース上で管理するためのエンティティです。
 * 入金テーブル（payments）と対応し、入金ID、請求ID、入金日、入金額の情報を持ちます。
 * 請求テーブル（Invoiceエンティティ）とリレーションを持っています。
 */

@Entity  // エンティティクラスであることを示すアノテーション
@Data   // Lombokを利用してゲッター・セッターを自動生成
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主キーの設定（自動生成）
    private Long id;  // 入金ID

    @ManyToOne
    @JoinColumn(name = "invoice_id")  // 請求IDとのリレーション設定
    private Invoice invoice;  // 対応する請求情報

    private LocalDate paymentDate;  // 入金日
    private Integer amount;         // 入金額
}
