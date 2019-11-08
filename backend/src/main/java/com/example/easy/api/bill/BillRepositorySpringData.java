package com.example.easy.api.bill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepositorySpringData extends JpaRepository<BillEntity,Integer> {
}
