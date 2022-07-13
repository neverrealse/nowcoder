package com.nowcoder.community.dao.Impl;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.stereotype.Repository;

@Repository("hb")
public class AlphaDaoImpl implements AlphaDao {
    @Override
    public String select() {
        return "aapp";
    }
}
