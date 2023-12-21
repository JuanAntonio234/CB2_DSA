package edu.upc.dsa;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session {

    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public void close() {

    }

    @Override
    public Object get(Class theClass, int ID) {
        return null;
    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public List<Object> findAll(Class theClass) {
        return null;
    }

    @Override
    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    @Override
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
