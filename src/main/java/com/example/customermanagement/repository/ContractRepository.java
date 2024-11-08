package com.example.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customermanagement.entity.Contract;

/**
 * Contract リポジトリインターフェース
 * Contract エンティティに対するデータ操作を行うリポジトリです。
 * Spring Data JPA を利用して、データベース操作を簡潔に行います。
 */
public interface ContractRepository extends JpaRepository<Contract, Long> {
    // 必要に応じてカスタムクエリメソッドを追加可能（例: findByCustomerなど）
}
