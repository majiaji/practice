//package com.fantasy.practice.jstorm;
//
//import backtype.storm.Config;
//import backtype.storm.LocalCluster;
//import backtype.storm.topology.TopologyBuilder;
//
///**
// * Created by jiaji on 2017/7/10.
// */
//public class LocalMain {
//    public static void main(String[] args) throws InterruptedException {
//        Config conf = new Config();
//        conf.setDebug(true);
//
//        TopologyBuilder builder = new TopologyBuilder();
//        builder.setSpout("spout", new TestSpout(), 1);
//        builder.setBolt("bolt", new TestBolt(), 1).shuffleGrouping("spout");
//
//        LocalCluster cluster = new LocalCluster();
//        cluster.submitTopology("SequenceTest", conf, builder.createTopology());
//
//        Thread.sleep(600000);
//
//        cluster.killTopology("SequenceTest");
//
//        cluster.shutdown();
//    }
//}
