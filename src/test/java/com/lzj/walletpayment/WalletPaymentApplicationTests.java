package com.lzj.walletpayment;

import com.lzj.walletpayment.entity.BankCard;
import com.lzj.walletpayment.entity.Orders;
import com.lzj.walletpayment.entity.Record;
import com.lzj.walletpayment.entity.Wallet;
import com.lzj.walletpayment.mapper.WalletMapper;
import com.lzj.walletpayment.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class WalletPaymentApplicationTests {

    @Resource
    private WalletService walletService;
    @Resource
    private WalletMapper walletMapper;

    @Test
    public void findWalletById(){
        Wallet wallet = walletService.findWalletById(1);
        System.out.println(wallet);
    }

    @Test
    public void findWalletByName(){
        Wallet wallet = walletService.findWalletByName("Jack");
        System.out.println(wallet);
    }

    @Test
    public void findOrderById(){
        Orders orderById = walletService.findOrderById(1);
        System.out.println(orderById);
    }

    @Test
    public void findRecordById(){
        List<Record> recordById = walletService.findRecordById(1);
        recordById.forEach(System.out::println);
    }

    @Test
    public void findBankCardByName(){
        BankCard bankCardByName = walletService.findBankCardByName("6222111");
        System.out.println(bankCardByName);
    }

    @Test
    public void addOrders(){
        Orders orders = new Orders();
        orders.setCommodity("椰子2");
        orders.setMoney(60);
        orders.setStatus("1");
        orders.setWId(2);
        //walletMapper.addOrders(orders);
        Wallet wallet = walletService.findWalletById(orders.getWId());
        walletService.addOrders(orders,wallet);
    }

    @Test
    public void refund(){
        Orders order = walletService.findOrderById(4);
        boolean result = walletService.refund(order);
        if (result){
            System.out.println("退款成功！！！");
        }
    }

    @Test
     public void chargeMoney(){
        double money = 100.0;
        BankCard card = walletMapper.findBankCardByName("6222111");
        boolean result = walletService.chargeMoney(card, money);
        if (result){
            System.out.println("充值成功！！！");
        }
    }

}
