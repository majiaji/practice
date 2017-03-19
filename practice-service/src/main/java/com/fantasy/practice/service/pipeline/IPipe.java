package com.fantasy.practice.service.pipeline;

/**
 * Created by jiaji on 16/12/23.
 */
public interface IPipe {
    default String getPipeName() {
        return this.getClass().getName();
    }

    void doPipe(PipeInput pipeInput, PipeOutput pipeOutput, IPipeIterator pipeIterator);
}
