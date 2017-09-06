package com.learn.motan.lambdaInterface;

/**
 * Created by yf003 on 2017/8/17.
 */
public interface ConfigProperties {

    <T>void setProperties(T val, Call<T> call);



}
