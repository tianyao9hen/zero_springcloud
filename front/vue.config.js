 module.exports = {
    baseUrl: './',
    productionSourceMap: false,
    devServer: {
        //port:8888,
        proxy: {
            '/api':{
                target:'http://127.0.0.1:8081',
                changeOrigin:true,
                pathRewrite:{
                    '^/api':''
                }
            },

            '/testApi':{
                target:'http://111.8.20.247:89/api/apph5api',
                changeOrigin:true,
                pathRewrite:{
                    '^/testApi':'/testApi'
                }
            },
            '/ms':{
                target: 'https://www.easy-mock.com/mock/592501a391470c0ac1fab128',
                changeOrigin: true
            }
        }
    },
    configureWebpack: {
        resolve: {
            alias: {
                'assets': '@/assets',
                'network': '@/network',
                'components': '@/components',
                'mixins': '@/mixins',
                'router': '@/router',
                'store': '@/store',
                'styles': '@/styles',
                'utils': '@/utils',
                'views': '@/views',
            }
        }
    }
}
