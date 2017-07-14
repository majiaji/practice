//package com.fantasy.practice.jstorm.fuck;
//
//import backtype.storm.Config;
//import com.alibaba.jstorm.batch.BatchTopologyBuilder;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// * Created by jiaji on 2017/7/10.
// */
//public class SimpleBatchTopologyTest {
//    @Test
//    public void testSimpleBatchTopology() {
//        BatchTopologyBuilder batchTopologyBuilder = new BatchTopologyBuilder("SimpleBatchTopology");
//        batchTopologyBuilder.setSpout("batchSpout", new SimpleBatchTestSpout(), 1);
//        batchTopologyBuilder.setBolt("batchBolt", new SimpleBatchTestBolt(), 2).shuffleGrouping("batchSpout");
//
//        Config config = new Config();
//        config.put(Config.TOPOLOGY_NAME, "SimpleBatchTopologyTest");
//        config.setMaxSpoutPending(1);
//
//        JStormUnitTestRunner.submitTopology(batchTopologyBuilder.getTopologyBuilder().createTopology(), config, 120, null);
//    }
//}