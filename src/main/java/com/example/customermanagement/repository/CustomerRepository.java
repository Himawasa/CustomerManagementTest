package com.example.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customermanagement.entity.Customer;

/**
 * Customer リポジトリインターフェース
 * Customer エンティティに対するデータ操作を行うリポジトリです。
 * Spring Data JPA を利用して、データベース操作を簡潔に行います。
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // ここにカスタムクエリメソッドを追加することができます（例: findByNameなど）
}
