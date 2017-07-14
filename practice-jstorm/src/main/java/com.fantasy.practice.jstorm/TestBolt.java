package com.fantasy.practice.jstorm;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by jiaji on 2017/7/10.
 */
public class TestBolt implements IBasicBolt {
    private static final Logger logger = LoggerFactory.getLogger(TestBolt.class);
    String curPos = TestBolt.class.getName();


    @Override
    public void prepare(Map stormConf, TopologyContext context) {

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        logger.error(curPos + "receive from pre:{}", input.getString(0));
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("BatchId", "Value"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
