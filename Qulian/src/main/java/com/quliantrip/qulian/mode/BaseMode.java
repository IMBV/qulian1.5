package com.quliantrip.qulian.mode;

import android.view.View;

// 泛型的类型表示的要添加数据的类型
public abstract class BaseMode<T> {
    public abstract View getModelView();

    public abstract void setData(T t);
}
