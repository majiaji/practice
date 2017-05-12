package com.fantasy.practice.service.pipeline;

import com.google.common.collect.Lists;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiaji on 16/12/23.
 */
public class PipeLine {
    private List<IPipe> pipeList = Lists.newLinkedList();

    public void setPipeList(List<IPipe> pipeList) {
        this.pipeList = pipeList;
    }

    @PostConstruct
    private void init() {
        if (pipeList.size() == 0) {
            throw new IllegalArgumentException("pipeline should have one pipe at least!");
        }
        //todo
        //找到所有实现Ipipe的类,放到pipeList中
    }

    public void execute(PipeInput pipeInput, PipeOutput pipeOutput) {
        PipeIterator pipeIterator = new PipeIterator(this, pipeInput, pipeOutput);
        //触发
        pipeIterator.next();
    }

    private class PipeIterator implements IPipeIterator {

        private PipeLine pipeLine;
        private PipeInput pipeInput;
        private PipeOutput pipeOutput;
        private AtomicInteger step = new AtomicInteger(0);

        PipeIterator(PipeLine pipeLine, PipeInput pipeInput, PipeOutput pipeOutput) {
            this.pipeLine = pipeLine;
            this.pipeInput = pipeInput;
            this.pipeOutput = pipeOutput;
        }

        @Override
        public void next() {
            int index = step.getAndIncrement();
            if (index < pipeLine.pipeList.size()) {
                IPipe pipe = pipeLine.pipeList.get(index);
                try {
                    pipe.doPipe(pipeInput, pipeOutput, this);
                } catch (Throwable t) {
                    t.printStackTrace();
                    //执行下一个pipe
                    next();
                }
            }
            if (index == pipeLine.pipeList.size()) {
                //最后一个pipe，特殊处理
                pipeInput.stopWatch.stop();
                System.out.println("end " + pipeInput.stopWatch.getTotalTimeMillis() + "ms");
            }
        }
    }
}
