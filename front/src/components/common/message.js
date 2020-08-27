/**
 * 向Vue中增加info、error、warn方法， 依赖element-ui的$message
 */
export default function (Vue) {

    Vue.prototype.info = function (msg) {
        this.$message({
            message: msg,
            type: "success"
        });
    };

    Vue.prototype.error = function (msg) {
        this.$message({
            message: msg,
            type: "error"
        });
    };

    Vue.prototype.warn = function (msg) {
        this.$message({
            message: msg,
            type: "warning"
        });
    }

}