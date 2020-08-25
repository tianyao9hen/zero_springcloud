package com.cloud.zero;

import com.cloud.zero.utils.RedisUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    private RedisUtil redisUtil;

    @org.junit.Test
    public void test1(){
        boolean test01 = redisUtil.set("test01", "123123123123");
        System.out.println("----------------------------------------------");
        System.out.println(test01);
    }
}