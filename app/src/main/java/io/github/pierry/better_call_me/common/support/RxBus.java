package io.github.pierry.better_call_me.common.support;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class RxBus {

  private final Relay<Object> _bus = PublishRelay.create().toSerialized();

  public void send(Object o) {
    _bus.accept(o);
  }

  public Flowable<Object> asFlowable() {
    return _bus.toFlowable(BackpressureStrategy.LATEST);
  }

  public boolean hasObservers() {
    return _bus.hasObservers();
  }
}
