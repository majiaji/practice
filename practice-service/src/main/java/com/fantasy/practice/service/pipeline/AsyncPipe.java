package com.fantasy.practice.service.pipeline;

/**
 * Created by jiaji on 16/12/26.
 */

/**
 * 在异步pipe中，不能修改调用上下文。否则会导致后边pipe上下文不准
 * 异步pipe一般用在写日志，发消息的业务里
 */
public abstract class AsyncPipe implements IPipe {

}
