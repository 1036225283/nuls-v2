package io.nuls.api.utils;

import io.nuls.api.ApiContext;
import io.nuls.api.cache.ApiCache;
import io.nuls.api.db.AgentService;
import io.nuls.api.manager.CacheManager;
import io.nuls.api.model.po.CoinContextInfo;
import io.nuls.core.core.ioc.SpringLiteContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class AssetTool {

    public static Map getNulsAssets() {
        ApiCache apiCache = CacheManager.getCache(ApiContext.defaultChainId);
        CoinContextInfo coinContextInfo = apiCache.getCoinContextInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("trades", coinContextInfo.getTxCount());
        map.put("totalAssets", AssetTool.toDouble(coinContextInfo.getTotal()));
        map.put("circulation", AssetTool.toDouble(coinContextInfo.getCirculation()));
        map.put("deposit", AssetTool.toDouble(coinContextInfo.getConsensusTotal()));
        map.put("business", AssetTool.toDouble(coinContextInfo.getBusiness()));
        map.put("team", AssetTool.toDouble(coinContextInfo.getTeam()));
        map.put("community", AssetTool.toDouble(coinContextInfo.getCommunity()));
        map.put("unmapped", AssetTool.toDouble(coinContextInfo.getUnmapped()));
        map.put("dailyReward", AssetTool.toDouble(coinContextInfo.getDailyReward()));
        int consensusCount = apiCache.getCurrentRound().getMemberCount() - apiCache.getChainInfo().getSeeds().size();
        if (consensusCount < 0) {
            consensusCount = 0;
        }
        map.put("consensusNodes", consensusCount);
        long count = 0;
        if (apiCache.getBestHeader() != null) {
            AgentService agentService = SpringLiteContext.getBean(AgentService.class);
            if (agentService != null) {
                count = agentService.agentsCount(ApiContext.defaultChainId, apiCache.getBestHeader().getHeight());
            }
        }
        map.put("totalNodes", count);
        return map;
    }

    public static double toDouble(BigInteger value) {
        return new BigDecimal(value).movePointLeft(8).setScale(8, RoundingMode.HALF_DOWN).doubleValue();
    }
}
