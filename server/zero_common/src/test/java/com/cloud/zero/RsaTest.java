package com.cloud.zero;

import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.utils.RsaUtils;
import org.junit.Test;

import java.util.UUID;

/**
 * RsaTest
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
public class RsaTest {

    @Test
    public void testGen() throws Exception {
        RsaUtils.generateKey(BaseConstant.PUB_KEY_PATH,BaseConstant.PRI_KEY_PATH, UUID.randomUUID().toString());
    }

}