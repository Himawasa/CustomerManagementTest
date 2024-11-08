package com.example.customermanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table; // インポートを追加

import lombok.Data;

/**
 * Invoice エンティティクラス
 * このクラスは、請求情報をデータベース上で管理するためのエンティティです。
 * 請求テーブル（invoices）と対応し、請求ID、契約ID、請求日、請求金額、支払期限、支払状況の情報を持ちます。
 * 契約テーブル（Contractエンティティ）とリレーションを持っています。
 */

@Entity  // エンティティクラスであることを示すアノテーション
@Table(name = "invoices")  // テーブル名を明示的に指定
@Data   // Lombokを利用してゲッター・セッターを自動生成
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主キーの設定（自動生成）
    private Long id;  // 請求ID

    @ManyToOne
    @JoinColumn(name = "contract_id")  // 契約IDとのリレーション設定
    private Contract contract;  // 対応する契約情報

    private LocalDate invoiceDate;  // 請求日
    private Integer amount;         // 請求金額
    private LocalDate dueDate;      // 支払期限
    private String status;          // 支払状況（未払い、支払済みなど）
}
