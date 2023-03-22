package com.lzj.walletpayment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lzj.walletpayment.mapper")
public class WalletPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletPaymentApplication.class, args);
    }

}
