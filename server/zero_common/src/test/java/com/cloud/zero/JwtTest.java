package com.cloud.zero;

/**
 * JwtTest
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
public class JwtTest {

    /*@Test
    public void TestDemo() throws Exception {
        AuthUserEntity userEntity = new AuthUserEntity();
        userEntity.setUsername("123");
        userEntity.setPassword("456");
        userEntity.setId(789L);
        String token = JwtUtils.generateToken(userEntity, RsaUtils.getPrivateKey(BaseConstant.PRI_KEY_PATH),10);
        System.out.println(token);
    }

    @Test
    public void TestDemo1() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJjbGFzcyI6ImNvbS5jbG91ZC56ZXJvLmVudGl0aWVzLmF1dGguQXV0aFVzZXJFbnRpdHkiLCJpZCI6Nzg5LCJwYXNzd29yZCI6IjQ1NiIsInVzZXJuYW1lIjoiMTIzIiwiZXhwIjoxNTk4NDA4MDkxfQ.I5k2BCFBjajEDgxjdh9pLnx9lUSRcDuvmUic-FcLwv6XYVyR5atYP7aZQxDZ2OesflQTi9ZgA0shBiX2rdPqxEvrP4TnSjBTt8UsQz7S-fPCmp2miifRokRG6uF9uYUfphFMLKhdZ9EO4GWvdyKMGizSqVVrmwSJBIHjQ00sP7Y";
        AuthUserEntity user = JwtUtils.getObjectFromToken(token, RsaUtils.getPublicKey(BaseConstant.PUB_KEY_PATH), AuthUserEntity.class);
        System.out.println(user);
        //System.out.println(user.getId());
        Boolean tokenExpired = JwtUtils.isTokenExpired(token, RsaUtils.getPublicKey(BaseConstant.PUB_KEY_PATH));
        System.out.println(tokenExpired);
        String s = JwtUtils.refreshToken(token, RsaUtils.getPublicKey(BaseConstant.PUB_KEY_PATH), RsaUtils.getPrivateKey(BaseConstant.PRI_KEY_PATH), AuthUserEntity.class, 1);
        System.out.println(s);
    }*/

}