package com.fantasy.practice.service.pipeline.pipe;

import com.fantasy.practice.service.pipeline.PipeInput;
import com.fantasy.practice.service.pipeline.PipeOutput;
import com.fantasy.practice.service.pipeline.SyncPipe;

/**
 * Created by jiaji on 16/12/26.
 */
public class StartWatchPipe extends SyncPipe {

    @Override
    public void doPipe(PipeInput pipeInput, PipeOutput pipeOutput) {
        pipeInput.stopWatch.start();
        System.out.println("sync pipe biz");
    }
}
