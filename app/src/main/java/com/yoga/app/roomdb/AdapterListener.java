package com.yoga.app.roomdb;

public interface AdapterListener {
    void onUpdate(Save save);
    void onDelete(double id ,int pos);
}
