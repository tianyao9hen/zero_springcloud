/*百度地图 key*/
const BAIDU_MAP_KEY = 'eHM6pwfsP5z2vQz6Gw9zOrofGX9pv7fo';
/*IP地址正则*/
const IS_IP_REG = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/
/*MAC地址的正则*/
const IS_MAC = /^([A-Z0-9]{2})-([A-Z0-9]{2})-([A-Z0-9]{2})-([A-Z0-9]{2})-([A-Z0-9]{2})-([A-Z0-9]{2})$/

/*jwt_token headerName*/
const TOKEN_HEADER_NAME = 'jwt_header_name';

/*jwt_token localStorage*/
const TOKEN_STORAGE_NAME = 'JWT_TOKEN';

export default {
    BAIDU_MAP_KEY,
    IS_IP_REG,
    IS_MAC,
    TOKEN_HEADER_NAME,
    TOKEN_STORAGE_NAME,
}