package com.lzj.walletpayment.controller;

import com.lzj.walletpayment.entity.ConsumeAndRefund;
import com.lzj.walletpayment.entity.Orders;
import com.lzj.walletpayment.entity.Record;
import com.lzj.walletpayment.entity.Wallet;
import com.lzj.walletpayment.service.WalletService;
import com.lzj.walletpayment.utils.BaseResponse;
import com.lzj.walletpayment.utils.StatusCode;
import com.lzj.walletpayment.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 钱包接口
 */

@Api(value = "钱包接口")
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Resource
    private WalletService walletService;

    /**
     * 查询钱包余额
     * @param username
     * @return
     */
    @GetMapping("/balance")
    public BaseResponse<Wallet> findBalance(@RequestParam(value="username") String username){
        // 查询余额失败：用户不存在
        if(StringUtils.isEmpty(username)){
            return ResultUtils.error(StatusCode.ERROR);
        }
        Wallet wallet = walletService.findWalletByName(username);
        if (wallet != null){
            return ResultUtils.success(wallet);
        }
        return ResultUtils.error(StatusCode.ERROR);
    }

    /**
     * 支付
     * @param money
     * @param wid
     * @param commodity
     * @return
     */
    @GetMapping("/pay")
    public BaseResponse<ConsumeAndRefund> pay(@RequestParam(value="money") double money,
                                              @RequestParam(value="wid") int wid,
                                              @RequestParam(value = "commodity") String commodity) {
        Wallet wallet = walletService.findWalletById(wid);
        // 支付失败：钱包余额 < 支付金额
        if (wallet.getBalance() < money){
            return ResultUtils.error(StatusCode.ERROR);
        }
        // 新增订单信息
        Date date = new Date();
        Orders order = new Orders();
        order.setCommodity(commodity);
        order.setMoney(money);
        order.setWId(wid);
        order.setStatus("1");
        order.setCreateTime(date);
        order.setUpdateTime(date);
        boolean result = walletService.addOrders(order, wallet);
        if (result){
            // 支付成功，将钱包和订单信息记录，新增记录信息
            Record record = new Record();
            record.setMoney(order.getMoney());
            record.setBalance(wallet.getBalance());
            record.setType("1");
            record.setWId(order.getWId());
            walletService.addRecord(record);
            // 支付成功，新增支付明细信息
            ConsumeAndRefund consumeAndRefund = new ConsumeAndRefund();
            consumeAndRefund.setUsername(wallet.getUsername());
            consumeAndRefund.setMoney(order.getMoney());
            consumeAndRefund.setTime(date);
            return ResultUtils.success(consumeAndRefund);
        }
        return ResultUtils.error(StatusCode.ERROR);
    }

    /**
     * 退款
     * @param oid
     * @return
     */
    @GetMapping("/refund")
    public BaseResponse<ConsumeAndRefund> refund(@RequestParam(value="oid") int oid) {
        Orders order = walletService.findOrderById(oid);
        // 退款失败：status = 1
        if (order.getStatus().equals("1")){
            return ResultUtils.error(StatusCode.ERROR);
        }
        boolean reuslt = walletService.refund(order);
        if (reuslt){
            // 退款成功，新增记录信息
            Wallet wallet = walletService.findWalletById(order.getWId());
            Record record = new Record();
            record.setMoney(order.getMoney());
            record.setBalance(wallet.getBalance());
            record.setType("2");
            record.setCreateTime(order.getCreateTime());
            record.setWId(order.getWId());
            walletService.addRecord(record);
            // 退款成功，新增退款明细信息
            ConsumeAndRefund consumeAndRefund = new ConsumeAndRefund();
            consumeAndRefund.setUsername(wallet.getUsername());
            consumeAndRefund.setMoney(order.getMoney());
            consumeAndRefund.setTime(order.getUpdateTime());
            return ResultUtils.success(consumeAndRefund);
        }
        return ResultUtils.error(StatusCode.ERROR);
    }

    /**
     * 查询钱包金额变动明细
     * @param wid
     * @return
     */
    @GetMapping("/moneyDetail")
    public BaseResponse<List<Record>> getMoneyDetails(@RequestParam(value="wid") int wid){
        List<Record> recordlist = walletService.findRecordById(wid);
        return ResultUtils.success(recordlist);
    }

}