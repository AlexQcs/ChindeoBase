package com.lazylibs.binding.command;

/** A one-argument action.
 * Created by zqs on 4/10/21
 */
public interface BindingConsumer<T> {
    void call(T t);
}
