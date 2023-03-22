package com.lzj.walletpayment.service.impl;

import com.lzj.walletpayment.entity.BankCard;
import com.lzj.walletpayment.entity.Orders;
import com.lzj.walletpayment.entity.Record;
import com.lzj.walletpayment.entity.Wallet;
import com.lzj.walletpayment.mapper.WalletMapper;
import com.lzj.walletpayment.service.WalletService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
* 实现接口
*/

@Service
public class WalletServiceImpl implements WalletService {

    @Resource
    private WalletMapper walletMapper;

    @Override
    public Wallet findWalletById(int wid) {
        return walletMapper.findWalletById(wid);
    }

    @Override
    public Wallet findWalletByName(String username) {
        return walletMapper.findWalletByName(username);
    }

    @Override
    public Wallet findWalletByCardName(String cardName) {
        return walletMapper.findWalletByCardName(cardName);
    }

    @Override
    public int updateWallet(Wallet wallet) {
        return walletMapper.updateWallet(wallet);
    }

    // 订单（购买）思路：钱包余额减少
    @Override
    public boolean addOrders(Orders orders, Wallet wallet) {
        // 如果订单插入成功
        if (walletMapper.addOrders(orders) > 0){
            // 获取钱包余额
            double balance = wallet.getBalance();
            // 付款后钱包余额 = 原钱包余额 - 订单价格
            wallet.setBalance(balance - orders.getMoney());
            // 将订单更新时间，同步给钱包
            wallet.setUpdateTime(orders.getUpdateTime());
            // 更新钱包信息
            walletMapper.updateWallet(wallet);
            walletMapper.updateOrders(orders);
            return true;
        }
        return false;
    }

    @Override
    public Orders findOrderById(int oid) {
        return walletMapper.findOrderById(oid);
    }

    @Override
    public List<Record> findRecordById(int wid) {
        return walletMapper.findRecordById(wid);
    }

    @Override
    public void addRecord(Record record) {
        walletMapper.addRecord(record);
    }

    @Override
    public BankCard findBankCardByName(String cardName) {
        return walletMapper.findBankCardByName(cardName);
    }

    // 退款思路：钱包余额 + 订单价格
    @Override
    public boolean refund(Orders order) {
        // 只有订单表更新成功，钱包余额才有变化
        if (walletMapper.updateOrders(order)) {
            // 查询订单表，对应的钱包
            Wallet wallet = walletMapper.findWalletById(order.getWId());
            // 订单的更新时间，同步给钱包
            wallet.setUpdateTime(order.getUpdateTime());
            // 此时wallet表中的balance = wallet.balance + order.money
            double balance = wallet.getBalance() + order.getMoney();
            // 重新给wallet表设置 相加后的balance
            wallet.setBalance(balance);
            // 更新wallet表，就能把钱退到钱包
            walletMapper.updateWallet(wallet);
            return true;
        }
        return false;
    }

    // 充值思路：银行卡余额减少，钱包余额增加；提现则相反
    @Override
    public boolean chargeMoney(BankCard card, double money) {
        // 如果银行卡余额为0，充值的金额 < 银行卡，则false
        if (card.getBalance() == 0 && card.getBalance() > money){
            // 查询此银行卡对应的钱包
            Wallet wallet = walletMapper.findWalletById(card.getWId());
            // 从银行卡，充值到钱包：银行卡余额减少，钱包余额增加
            double cardBalance = card.getBalance() - money;
            double walletBalance = wallet.getBalance() + money;
            // 分别设置银行卡、钱包的余额
            card.setBalance(cardBalance);
            wallet.setBalance(walletBalance);
            // 银行卡更新时间，同步给钱包
            wallet.setUpdateTime(card.getUpdateTime());
            // 如果银行卡更新成功，则对钱包进行更新
            if (walletMapper.updateBankCard(card)) {
                walletMapper.updateWallet(wallet);
                System.out.println("111");
                return true;
            }
        }
        return false;
    }

    // 钱包金额明细思路：
    // 1.余额增加的情况：用银行卡充值，退款成功；
    // 2.余额减少的情况：提现到银行卡，付款成功
    @Override
    public boolean moneyDetails(Wallet wallet, String cardName, double money) {
        if(wallet.getBalance() == 0 && wallet.getBalance() > money){
            BankCard bankCard = new BankCard();
            // 根据银行卡名称，获取银行卡
            bankCard = walletMapper.findBankCardByName(cardName);
            // 钱包，余额减少
            double walletBalance = wallet.getBalance() - money;
            // 银行卡，余额增加
            double bankCardBalance = bankCard.getBalance() + money;
            // 分别设置钱包、银行卡的余额
            wallet.setBalance(walletBalance);
            bankCard.setBalance(bankCardBalance);
            // 钱包更新时间，同步给银行卡
            bankCard.setUpdateTime(wallet.getUpdateTime());
            // 如果钱包更新成功，则对银行卡进行更新
            if (walletMapper.updateWallet(wallet) > 0){
                walletMapper.updateBankCard(bankCard);
                return true;
            }
        }
        return false;   // 钱包余额不足
    }
}




