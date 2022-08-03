package com.example.dblocking.request;


import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundWalletRequest {

    private String email;
    private BigDecimal amount;

}
