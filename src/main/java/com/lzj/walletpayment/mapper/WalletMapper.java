package com.lzj.walletpayment.mapper;

import com.lzj.walletpayment.entity.BankCard;
import com.lzj.walletpayment.entity.Orders;
import com.lzj.walletpayment.entity.Record;
import com.lzj.walletpayment.entity.Wallet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* WalletMapper
*/

public interface WalletMapper {

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
     * @return
     */
    int addOrders(Orders order);

    /**
     * 6.更新订单
     * @param orders
     * @return
     */
    boolean updateOrders(Orders orders);

    /**
     * 7.根据oid，查询订单信息
     * @param oid
     * @return
     */
    Orders findOrderById(int oid);

    /**
     * 8.添加消费记录信息
     * @param record
     */
    void addRecord(Record record);

    /**
     * 9.根据wid，查询消费记录信息
     * @param wid
     * @return
     */
    List<Record> findRecordById(int wid);

    /**
     * 10.更新银行卡信息
     * @param bankCard
     * @return
     */
    boolean updateBankCard(BankCard bankCard);

    /**
     * 11.根据cardName，查询银行卡信息
     * @param cardName
     * @return
     */
    BankCard findBankCardByName(String cardName);
    
}




