/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc.cluster.loadbalance;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcStatus;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * LeastActiveLoadBalance
 * <p>
 * Filter the number of invokers with the least number of active calls and count the weights and quantities of these invokers.
 * If there is only one invoker, use the invoker directly;
 * if there are multiple invokers and the weights are not the same, then random according to the total weight;
 * if there are multiple invokers and the same weight, then randomly called.
 */
public class LeastActiveLoadBalance extends AbstractLoadBalance {

    public static final String NAME = "leastactive";

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        // 最小的活跃数
        // Number of invokers
        int length = invokers.size();

        // 最小的活跃数
        // The least active value of all invokers
        int leastActive = -1;

        // 具有相同“最小活跃数”的服务者提供者（以下用 Invoker 代称）数量
        // The number of invokers having the same least active value (leastActive)
        int leastCount = 0;

        // leastIndexs 用于记录具有相同“最小活跃数”的 Invoker 在 invokers 列表中的下标信息
        // The index of invokers having the same least active value (leastActive)
        int[] leastIndexes = new int[length];

        // the weight of every invokers
        int[] weights = new int[length];

        // The sum of the warmup weights of all the least active invokes
        int totalWeight = 0;

        // 第一个最小活跃数的 Invoker 权重值，用于与其他具有相同最小活跃数的 Invoker 的权重进行对比，
        // 以检测是否“所有具有相同最小活跃数的 Invoker 的权重”均相等
        // The weight of the first least active invoke
        int firstWeight = 0;

        // Every least active invoker has the same weight value?
        boolean sameWeight = true;

        // 遍历 invokers 列表
        // Filter out all the least active invokers
        for (int i = 0; i < length; i++) {
            Invoker<T> invoker = invokers.get(i);

            // 获取 Invoker 对应的活跃数
            // Get the active number of the invoke
            int active = RpcStatus.getStatus(invoker.getUrl(), invocation.getMethodName()).getActive();

            // afterWarmup 等价于上面的 weight 变量，这样命名是为了强调该变量经过了 warmup 降权处理
            // Get the weight of the invoke configuration. The default value is 100.
            int afterWarmup = getWeight(invoker, invocation);

            // save for later use
            weights[i] = afterWarmup;

            // 发现更小的活跃数，重新开始
            // If it is the first invoker or the active number of the invoker is less than the current least active number
            if (leastActive == -1 || active < leastActive) {
                // 使用当前活跃数 active 更新最小活跃数 leastActive
                // Reset the active number of the current invoker to the least active number
                leastActive = active;

                // 更新 leastCount 为 1
                // Reset the number of least active invokers
                leastCount = 1;

                // 记录当前下标值到 leastIndexs 中
                // Put the first least active invoker first in leastIndexes
                leastIndexes[0] = i;

                // Reset totalWeight
                totalWeight = afterWarmup;
                // Record the weight the first least active invoker
                firstWeight = afterWarmup;
                // Each invoke has the same weight (only one invoker here)
                sameWeight = true;
                // If current invoker's active value equals with leaseActive, then accumulating.
            } else if (active == leastActive) { // 当前 Invoker 的活跃数 active 与最小活跃数 leastActive 相同

                // 在 leastIndexs 中记录下当前 Invoker 在 invokers 集合中的下标
                // Record the index of the least active invoker in leastIndexes order
                leastIndexes[leastCount++] = i;

                // 累加权重
                // Accumulate the total weight of the least active invoker
                totalWeight += afterWarmup;


                // 检测当前 Invoker 的权重与 firstWeight 是否相等，
                // 不相等则将 sameWeight 置为 false
                // If every invoker has the same weight?
                if (sameWeight && i > 0 && afterWarmup != firstWeight) {
                    sameWeight = false;
                }
            }
        }

        // 当只有一个 Invoker 具有最小活跃数，此时直接返回该 Invoker 即可
        // Choose an invoker from all the least active invokers
        if (leastCount == 1) {
            // If we got exactly one invoker having the least active value, return this invoker directly.
            return invokers.get(leastIndexes[0]);
        }

        // 有多个 Invoker 具有相同的最小活跃数，但它们之间的权重不同
        if (!sameWeight && totalWeight > 0) {

            // 随机生成一个 [0, totalWeight) 之间的数字
            // If (not every invoker has the same weight & at least one invoker's weight>0), select randomly based on
            // totalWeight.
            int offsetWeight = ThreadLocalRandom.current().nextInt(totalWeight);

            // 循环让随机数减去具有最小活跃数的 Invoker 的权重值，
            // 当 offset 小于等于0时，返回相应的 Invoker
            // Return a invoker based on the random value.
            for (int i = 0; i < leastCount; i++) {
                int leastIndex = leastIndexes[i];
                offsetWeight -= weights[leastIndex];
                if (offsetWeight < 0) {
                    return invokers.get(leastIndex);
                }
            }
        }

        // 如果权重相同或权重为0时，随机返回一个 Invoker
        // If all invokers have the same weight value or totalWeight=0, return evenly.
        return invokers.get(leastIndexes[ThreadLocalRandom.current().nextInt(leastCount)]);
    }
}