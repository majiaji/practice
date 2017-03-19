package com.fantasy.practice.service.pipeline;

/**
 * Created by jiaji on 16/12/26.
 */
public abstract class SyncPipe implements IPipe {
    @Override
    public void doPipe(PipeInput pipeInput, PipeOutput pipeOutput, IPipeIterator pipeIterator) {
        doPipe(pipeInput, pipeOutput);
        pipeIterator.next();
    }

    public abstract void doPipe(PipeInput pipeInput, PipeOutput pipeOutput);
}
