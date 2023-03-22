package com.lzj.walletpayment.service;

import com.lzj.walletpayment.entity.BankCard;
import com.lzj.walletpayment.entity.Record;
import com.lzj.walletpayment.entity.Wallet;
import com.lzj.walletpayment.entity.Orders;
import java.util.List;

/**
* 定义接口
*/

public interface WalletService {

    /**
     * 1.根据wid，查询钱包信息
     * @param wid
     * @return
     */
    Wallet findWalletById(int wid);

    /**
     * 2.根据username，查询钱包信息
     * @param username
     * @return
     */
    Wallet findWalletByName(String username);

    /**
     * 3.根据cardName，查询钱包信息
     * @param cardName
     * @return
     */
    Wallet findWalletByCardName(String cardName);

    /**
     * 4.更新钱包
     * @param wallet
     * @return
     */
    int updateWallet(Wallet wallet);

    /**
     * 5.添加订单
     * @param order
     * @param wallet
     * @return
     */
    boolean addOrders(Orders order, Wallet wallet);

    /**
     * 6.根据oid，查询订单信息
     * @param oid
     * @return
     */
    Orders findOrderById(int oid);

    /**
     * 7.根据wid，查询消费记录信息
     * @param wid
     * @return
     */
    List<Record> findRecordById(int wid);

    /**
     * 8.添加消费记录信息
     * @param record
     */
    void addRecord(Record record);

    /**
     * 9.根据cardName，查询银行卡信息
     * @param cardName
     * @return
     */
    BankCard findBankCardByName(String cardName);

    /**
     * 10.退款
     * @param order
     * @return
     */
    boolean refund(Orders order);

    /**
     * 11.充值
     * @param card
     * @param money
     * @return
     */
    boolean chargeMoney(BankCard card, double money);

    /**
     * 12.金额明细
     * @param wallet
     * @param cardName
     * @param money
     * @return
     */
    boolean moneyDetails(Wallet wallet, String cardName, double money);
}