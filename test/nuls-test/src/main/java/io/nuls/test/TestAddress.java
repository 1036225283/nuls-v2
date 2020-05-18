package io.nuls.test;

import io.nuls.core.basic.Result;
import io.nuls.v2.NulsSDKBootStrap;
import io.nuls.v2.model.dto.AccountDto;
import io.nuls.v2.service.AccountService;
import io.nuls.v2.util.NulsSDKTool;

import java.math.BigDecimal;
import java.util.List;

public class TestAddress {

    public static void main(String[] args) {
        createAddress2();
    }

    public static void getPriKey() {
        NulsSDKBootStrap.init(3, "");

        AccountService instance = AccountService.getInstance();
        Result result = instance.getPriKeyOffline("LUCKdGs31KiNzdfDh1L2Fxp5zKxTKftf4aQAZ", "b03e2219132118179367c4450d62152e2967c8f555451b4e5299b7cee2e2f1764174673b92f1fa670200c4cd5ea76cff", "Luck123456@r$");
        System.out.println("priKey = " + result.getData());
    }

    public static void createAddress() {
        NulsSDKBootStrap.init(3, "");
        Result<List<AccountDto>> result = NulsSDKTool.createOffLineAccount(20, "LUCK", "Luck123456@r$");
        List<AccountDto> list = result.getData();
        for (AccountDto account : list) {
            System.out.println(account.getAddress() + " \tprivateKey = " + account.getPrikey() + " \tencKey = " + account.getEncryptedPrivateKey());
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setEncryptedPrivateKey("");
        accountDto.getPrikey();
    }

    public static void createAddress2() {
        NulsSDKBootStrap.init(6, "");
        Result<List<AccountDto>> result = NulsSDKTool.createOffLineAccount(20, "SOME", "Luck123456@r$");
        List<AccountDto> list = result.getData();
        for (AccountDto account : list) {
            System.out.println(account.getAddress() + " \tprivateKey = " + account.getPrikey() + " \tencKey = " + account.getEncryptedPrivateKey());
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setEncryptedPrivateKey("");
        accountDto.getPrikey();
    }
}
