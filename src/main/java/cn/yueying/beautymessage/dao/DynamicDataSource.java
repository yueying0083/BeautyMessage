package cn.yueying.beautymessage.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by luojian on 2017/4/18.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String DATASOURCE_MANAGER = "dataSourceManager";

    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomType() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getCustomerType();
    }
}
