package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public final class ServiceOrdersRepository {

    public static boolean showActive = true;

    private static class Singleton {
        public static final ServiceOrdersRepository INSTANCE = new ServiceOrdersRepository();
    }

    private ServiceOrdersRepository() {
        super();
    }

    public static ServiceOrdersRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public void save(ServiceOrder serviceOrder) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        if (serviceOrder.getId() == null) {
            db.insert(ServiceOrderContract.TABLE, null, ServiceOrderContract.getContentValues(serviceOrder));
        } else {
            String where = ServiceOrderContract.ID + " = ?";
            String[] args = {serviceOrder.getId().toString()};
            db.update(ServiceOrderContract.TABLE, ServiceOrderContract.getContentValues(serviceOrder), where, args);
        }
        db.close();
        helper.close();
    }

    public void archive(ServiceOrder serviceOrder){
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = ServiceOrderContract.ID + " = ?";
        String[] args = {serviceOrder.getId().toString()};
        db.update(ServiceOrderContract.TABLE, ServiceOrderContract.getContentValues(serviceOrder), where, args);
        db.close();
        helper.close();
    }

    public List<ServiceOrder> getAll() {
        String active = (showActive) ? "1" : "0";

        String whereClause = " active = ? ";
        String[] whereArgs = new String[]{ active };

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(ServiceOrderContract.TABLE, ServiceOrderContract.COLUMNS, whereClause, whereArgs, null, null, ServiceOrderContract.DATE);
        List<ServiceOrder> serviceOrders = ServiceOrderContract.bindList(cursor);

        db.close();
        helper.close();
        return serviceOrders;
    }

}