package com.sample.xa.storage.service;

import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deduct(String commodityCode, int count) {
        String xid = RootContext.getXID();
        LOGGER.info("deduct storage balance in transaction: " + xid);
        jdbcTemplate.update("update storage_tbl set count = count - ? where commodity_code = ?", new Object[] { count, commodityCode});
    }
}
