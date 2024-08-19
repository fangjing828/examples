package com.examples.protobuf.risk.lending;

import com.google.common.cache.Cache;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Getter
@ToString
public class UnitUserAsset implements Cloneable {
    /**
     * 机构风险单元唯一key
     */
    private long instUnitUniqueKey;

    private boolean isValid = true;

    /**
     * partitionOffset
     * 当前消费队列对应pnl的offset
     */
    private long pnlPartitionOffset = -1;

    /**
     * partitionOffset
     * 当前消费队列对应hdts的offset
     */
    private long hdtsPartitionOffset = -1;

    /**
     * requestPartitionOffset
     * 当前消费队列对应request的offset
     * 每个数据分片一定要独立offset，一定要追平以后才能对外服务！！！！
     */
    private long requestPartitionOffset = -1;

    /**
     * 强平动作行为
     */
    private String riskFlowAction;

    /**
     * 强平状态执行状态
     */
    private String liqTransStatus;

    /**
     * 机构风险单元资产模型列表
     * key uid
     * value 用户资产模型
     */
    private Map<Long, String> userAssetModelMap = null;

    /**
     * 平仓结果+兑币结果
     */
    private Map<String, String> exchangeResult = null;

    /**
     * 借款本金
     */
    private Map<String, BigDecimal> loanPrincipalMap = null;

    /**
     * 借款利息
     */
    private Map<String, BigDecimal> loanInterestMap = null;

    /**
     * 处于UTA中的强平用户数据
     */
    private List<Long> utaLiqUsers = new ArrayList<>(8);

    /**
     * 历史数据,用户幂等和dump
     * 不会深拷贝，会直接更新，如果数据更新但是offset未更新，则数据会变成最新的，也会被dump下去，这里面的数据会领先offset，理论上不影响逻辑
     * 写入数据时，会出现先写进来，但是整体处理失败，这种情况下发生要么是代码bug，要么是重启。重启会自动恢复到active的map和list里。切记写完kafka要立刻保存到内存，不要做其他操作
     * dump List<RequestModelCluster> 按照activeRequestList的顺序从前往后排，不在activeRequestList里面的往后随机排放,去重RequestModelCluster
     * cache的key是reqId，不是clusterId
     */
//    private Map<String, Cache<String, RequestModelCluster>> requestModelMap = Maps.newConcurrentMap();

    /**
     * 历史数据,用户幂等和dump
     * 不会深拷贝，会直接更新，如果数据更新但是offset未更新，则数据会变成最新的，也会被dump下去，这里面的数据会领先offset，理论上不影响逻辑
     * 写入数据时，会出现先写进来，但是整体处理失败，这种情况下发生要么是代码bug，要么是重启。重启会自动恢复到active的map和list里。切记写完kafka要立刻保存到内存，不要做其他操作
     * dump List<RequestModelCluster> 按照activeRequestList的顺序从前往后排，不在activeRequestList里面的往后随机排放,去重RequestModelCluster
     * cache的key是reqId，不是clusterId
     */
    private Map<String, Cache<String, String>> hisRequestModelMap = Maps.newConcurrentMap();

    /**
     * 深拷贝 活跃请求集的map,到终态以后需要把requestModelCluster下面所有的reqId从map中清除
     * 第一层key type 第二层key是 reqId
     * 不需要dump
     */
    private Map<String, Map<String, String>> activeClusterMap = null;

    /**
     * 深拷贝 活跃请求集的map,所有到终态以后需要把移除
     * key flowCode+clusterId
     * 不需要dump
     */
    private Map<String, Map<String, String>> activeClusterIdClusterMap = null;

    /**
     * 深拷贝 当activeClusterMap中的某一个cluster已经到终态了,要activeRequestList移除对应的数据
     * 不需要dump
     */
    private PriorityQueue<String> activeRequestQueue = null;

    public UnitUserAsset() {
        userAssetModelMap = Maps.newHashMap();
        exchangeResult = Maps.newHashMap();
        loanPrincipalMap = Maps.newHashMap();
        loanInterestMap = Maps.newHashMap();
        activeClusterMap = Maps.newHashMap();
        activeClusterIdClusterMap = Maps.newHashMap();
        activeRequestQueue = Queues.newPriorityQueue();
    }

    public UnitUserAsset shallowCopy() {
        UnitUserAsset instUnitUserAssetModel = new UnitUserAsset();
        instUnitUserAssetModel.instUnitUniqueKey = this.instUnitUniqueKey;
        instUnitUserAssetModel.isValid = this.isValid;
        instUnitUserAssetModel.pnlPartitionOffset = this.pnlPartitionOffset;
        instUnitUserAssetModel.hdtsPartitionOffset = this.hdtsPartitionOffset;
        instUnitUserAssetModel.requestPartitionOffset = this.requestPartitionOffset;
        instUnitUserAssetModel.liqTransStatus = this.liqTransStatus;
        instUnitUserAssetModel.riskFlowAction = this.riskFlowAction;

        //浅拷贝
        instUnitUserAssetModel.hisRequestModelMap = this.hisRequestModelMap;

        //在实际使用的时候进行拷贝处理，用哪个拷贝哪个
        if (isNotEmpty(this.userAssetModelMap)) {
            instUnitUserAssetModel.userAssetModelMap = new HashMap<>(this.userAssetModelMap.size());
            instUnitUserAssetModel.userAssetModelMap.putAll(this.userAssetModelMap);
        } else {
            instUnitUserAssetModel.userAssetModelMap = new HashMap<>();
        }

        if (isNotEmpty(this.exchangeResult)) {
            instUnitUserAssetModel.exchangeResult = new HashMap<>(this.exchangeResult.size());
            instUnitUserAssetModel.exchangeResult.putAll(this.exchangeResult);
        } else {
            instUnitUserAssetModel.exchangeResult = new HashMap<>();
        }

        if (isNotEmpty(this.loanPrincipalMap)) {
            instUnitUserAssetModel.loanPrincipalMap = new HashMap<>(this.loanPrincipalMap.size());
            instUnitUserAssetModel.loanPrincipalMap.putAll(this.loanPrincipalMap);
        } else {
            instUnitUserAssetModel.loanPrincipalMap = new HashMap<>();
        }

        if (isNotEmpty(this.loanInterestMap)) {
            instUnitUserAssetModel.loanInterestMap = new HashMap<>(this.loanInterestMap.size());
            instUnitUserAssetModel.loanInterestMap.putAll(this.loanInterestMap);
        } else {
            instUnitUserAssetModel.loanInterestMap = new HashMap<>();
        }

        if (isNotEmpty(this.activeClusterMap)) {
            instUnitUserAssetModel.activeClusterMap = new HashMap<>(this.activeClusterMap.size());
            for (Map.Entry<String, Map<String, String>> entry : this.activeClusterMap.entrySet()) {
                instUnitUserAssetModel.activeClusterMap.put(entry.getKey(), new HashMap<>(entry.getValue()));
            }
        } else {
            instUnitUserAssetModel.activeClusterMap = new HashMap<>();
        }

        if (isNotEmpty(this.activeClusterIdClusterMap)) {
            instUnitUserAssetModel.activeClusterIdClusterMap = new HashMap<>(this.activeClusterIdClusterMap.size());
            for (Map.Entry<String, Map<String, String>> entry :
                    this.activeClusterIdClusterMap.entrySet()) {
                instUnitUserAssetModel.activeClusterIdClusterMap.put(entry.getKey(), new HashMap<>(entry.getValue()));
            }
        } else {
            instUnitUserAssetModel.activeClusterIdClusterMap = new HashMap<>();
        }

        if (activeRequestQueue != null && !activeRequestQueue.isEmpty()) {
            instUnitUserAssetModel.activeRequestQueue = Queues.newPriorityQueue(this.activeRequestQueue);
        } else {
            activeRequestQueue = Queues.newPriorityQueue();
        }

        return instUnitUserAssetModel;
    }

    @SneakyThrows
    public UnitUserAsset clone() {
        UnitUserAsset result = (UnitUserAsset) super.clone();
        result.isValid = true;
        return result;
    }


    @SneakyThrows
    public UnitUserAsset copy() {
        UnitUserAsset instUnitUserAssetModel = new UnitUserAsset();
        instUnitUserAssetModel.instUnitUniqueKey = this.instUnitUniqueKey;
        instUnitUserAssetModel.isValid = this.isValid;
        instUnitUserAssetModel.pnlPartitionOffset = this.pnlPartitionOffset;
        instUnitUserAssetModel.hdtsPartitionOffset = this.hdtsPartitionOffset;
        instUnitUserAssetModel.requestPartitionOffset = this.requestPartitionOffset;
        instUnitUserAssetModel.liqTransStatus = this.liqTransStatus;
        instUnitUserAssetModel.riskFlowAction = this.riskFlowAction;
        instUnitUserAssetModel.userAssetModelMap = this.userAssetModelMap;
        instUnitUserAssetModel.exchangeResult = exchangeResult;
        instUnitUserAssetModel.loanPrincipalMap = loanPrincipalMap;
        instUnitUserAssetModel.loanInterestMap = loanInterestMap;
        instUnitUserAssetModel.utaLiqUsers = utaLiqUsers;
        instUnitUserAssetModel.hisRequestModelMap = this.hisRequestModelMap;
        instUnitUserAssetModel.activeClusterMap = this.activeClusterMap;
        instUnitUserAssetModel.activeClusterIdClusterMap = activeClusterIdClusterMap;
        instUnitUserAssetModel.activeRequestQueue = this.activeRequestQueue;
        return instUnitUserAssetModel;
    }

    public boolean isNotEmpty(Map map) {
        return map != null && !map.isEmpty();
    }
}
