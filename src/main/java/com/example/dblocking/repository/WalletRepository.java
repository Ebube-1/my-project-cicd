package com.example.dblocking.repository;

import com.example.dblocking.model.AppUser;
import com.example.dblocking.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Wallet wallet set wallet.balance= :balance , wallet.version = (:version +1) where wallet.id =:id and wallet.version =:version ")

    int updateWallet (@Param("balance") BigDecimal balance, @Param("version")int version, @Param("id") Long id);
}
