package com.example.customermanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * Contract エンティティクラス
 * このクラスは、契約情報をデータベース上で管理するためのエンティティです。
 * 契約テーブル（contracts）と対応し、契約ID、顧客ID、契約開始日、契約終了日、契約ステータス、会員ランク、
 * 更新通知フラグの情報を持ちます。顧客テーブル（Customerエンティティ）とリレーションを持っています。
 */

@Entity  // エンティティクラスであることを示すアノテーション
@Table(name = "contracts")  // テーブル名を明示
@Data   // Lombokを利用してゲッター・セッターを自動生成
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主キーの設定（自動生成）
    private Long id;  // 契約ID

    @ManyToOne
    @JoinColumn(name = "customer_id")  // 顧客IDとのリレーション設定
    private Customer customer;  // 契約者の顧客情報

    private LocalDate startDate;  // 契約開始日
    private LocalDate endDate;    // 契約終了日
    private String status;        // 契約ステータス（有効、失効など）
    private String rank;          // 会員ランク（Bronze, Silver, Gold）
    private Boolean renewalAlert; // 更新通知フラグ
}
