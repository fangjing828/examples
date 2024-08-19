package com.examples.bybit.trade;

import com.bybit.api.client.config.BybitApiConfig;
import com.bybit.api.client.domain.account.AccountType;
import com.bybit.api.client.domain.account.request.AccountDataRequest;
import com.bybit.api.client.domain.asset.WithBonus;
import com.bybit.api.client.domain.asset.request.AssetDataRequest;
import com.bybit.api.client.restApi.BybitApiAccountRestClient;
import com.bybit.api.client.restApi.BybitApiAssetRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;

public class CollateralCoin {

    private static final BybitApiClientFactory factory = BybitApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", BybitApiConfig.MAINNET_DOMAIN);
    public static void main(String[] args) {
        BybitApiAccountRestClient accountRestClient = factory.newAccountRestClient();
        {
            var transactionLogRequest = AccountDataRequest.builder().build();
            var result = accountRestClient.getTransactionLog(transactionLogRequest);
            System.out.println(result);
        }

        BybitApiAssetRestClient assetRestClient = factory.newAssetRestClient();
        {
            var SingleCoinBalanceRequest = AssetDataRequest.builder().accountType(AccountType.UNIFIED).coin("BTC").withBonus(WithBonus.QUERY).build();
            var SingleCoinBalance = assetRestClient.getAssetSingleCoinBalance(SingleCoinBalanceRequest);
            System.out.println(SingleCoinBalance);
        }

        {
            var allCoinsBalanceRequest = AssetDataRequest.builder().accountType(AccountType.UNIFIED).withBonus(WithBonus.QUERY).build();
            var allCoinsBalance = assetRestClient.getAssetAllCoinsBalance(allCoinsBalanceRequest);
            System.out.println(allCoinsBalance);
        }
    }


}
