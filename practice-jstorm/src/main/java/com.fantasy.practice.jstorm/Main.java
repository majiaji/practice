//package com.fantasy.practice.jstorm;
//
//import backtype.storm.Config;
//import backtype.storm.StormSubmitter;
//import backtype.storm.generated.AlreadyAliveException;
//import backtype.storm.generated.InvalidTopologyException;
//import backtype.storm.topology.BoltDeclarer;
//import backtype.storm.topology.SpoutDeclarer;
//import backtype.storm.topology.TopologyBuilder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//    public static void main(String[] args) {
//        Map conf = new HashMap();
//        //topology所有自定义的配置均放入这个Map
//
//        TopologyBuilder builder = new TopologyBuilder();
//        //创建topology的生成器
//
//        int spoutParal = 1;
//        //获取spout的并发设置
//
//        SpoutDeclarer spout = builder.setSpout(TestSpout.class.getName(), new TestSpout(), spoutParal);
//        //创建Spout， 其中new TestSpout() 为真正spout对象，TestSpout.class.getName() 为spout的名字，注意名字中不要含有空格
//
//        int boltParal = 1;
//        //获取bolt的并发设置
//        BoltDeclarer testBolt = builder.setBolt(TestBolt.class.getName(), new TestBolt(), boltParal).shuffleGrouping(TestSpout.class.getName());
//        //创建bolt， TestBolt.class.getName()为bolt名字，TotalCount 为bolt对象，boltParal为bolt并发数，
//        //shuffleGrouping（TestSpout.class.getName()），
//        //表示接收TestSpout.class.getName()的数据，并且以shuffle方式，
//        //即每个spout随机轮询发送tuple到下一级bolt中
//
//        int ackerParal = 1;
//        Config.setNumAckers(conf, ackerParal);
//        //设置表示acker的并发数
//
//        int workerNum = 10;
//        conf.put(Config.TOPOLOGY_WORKERS, workerNum);
//        //表示整个topology将使用几个worker
//
//        conf.put(Config.STORM_CLUSTER_MODE, "distributed");
//        //设置topolog模式为分布式，这样topology就可以放到JStorm集群上运行
//        try {
//            StormSubmitter.submitTopology("helloWorld", conf, builder.createTopology());
//        } catch (AlreadyAliveException | InvalidTopologyException e) {
//            e.printStackTrace();
//        }
//    }
//}
