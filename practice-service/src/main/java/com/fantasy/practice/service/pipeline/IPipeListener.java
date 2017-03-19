package com.fantasy.practice.service.pipeline;

/**
 * Created by jiaji on 16/12/23.
 */
public interface IPipeListener {
    void onStart(PipeInput pipeInput, PipeOutput pipeOutput);

    void onComplete(PipeInput pipeInput, PipeOutput pipeOutput);

    void onError(PipeInput pipeInput, PipeOutput pipeOutput);
}
