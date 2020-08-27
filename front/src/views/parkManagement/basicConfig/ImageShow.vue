<template>
    <div class="image-show">
        <div class="image-list" :style="imageListWidth">
            <div class="image-item" v-for="item of fileList">
                <el-image class="image-body" :src="item.url" :preview-src-list="previewUrlList(item)"></el-image>
                <i class="el-icon-delete" v-if="canEdit" @click="deleteImage(item)"></i>
            </div>
            <el-upload
                    :action="uploadUrl"
                    class="image-upload"
                    list-type="picture-card"
                    :multiple="true"
                    :accept="accept"
                    :show-file-list="false"
                    :on-success="uploadSuccess"
                    :on-error="uploadError"
                    :on-exceed="uploadExceed"
                    :limit="limit"
                    v-if="canEdit"
            >
                <i class="el-icon-plus upload-icon"></i>
            </el-upload>
        </div>
        <!--<el-dialog :visible.sync="dialogVisible" :fullscreen="true">
            <img style="height: 100%;width: 100%" :src="dialogImageUrl" alt="">
        </el-dialog>-->
    </div>
</template>

<script>
    export default {
        name: "ImageShow",
        data(){
            return {
                uploadUrl: 'http://1.w2wz.com/upload.php',
                accept: '.jpg,.jpeg,.png,.gif,.JPG,.JPEG,.GIF,.PNG',
                limit: 5,
                isEnable: true,
                fileList: [
                    {
                        name: 'food.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    },
                    {
                        name: 'food2.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    },
                    {
                        name: 'food2.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    },
                    {
                        name: 'food2.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    },
                    {
                        name: 'food2.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    },
                    {
                        name: 'food2.jpeg',
                        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
                    },
                ]
            }
        },
        props: {
            canEdit: {
                type: Boolean,
                default(){
                    return false;
                }
            }
        },
        computed: {
            imageListWidth(){
                return {
                    width: 210 * (this.fileList.length + (this.canEdit?1:0)) + 'px'
                }
            },

        },
        methods: {
            previewUrlList(item){
                return [
                    item.url
                ]
            },
            deleteImage(item){
                this.$confirm('是否删除该图片，一旦删除将无法找回？', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        message: '图片已删除！',
                        type: 'success'
                    });
                }).catch(() => {
                    this.$message.error('图片删除失败！');
                })

            },
            uploadRemove(file, fileList) {
                //移除
                console.log(file, fileList);
            },
            uploadSuccess(response, file, fileList){
                console.log(response,file, fileList);
                //上传成功
            },
            uploadError(err, file, fileList){
                console.log(err,file, fileList);
                //上传失败
            },
            uploadExceed(files, fileList){
                console.log(files, fileList);
                //当选中数量超过上限时
            }
        }
    }
</script>

<style scoped>
    .image-show {
        width: 100%;
        height: 150px;
        overflow: auto;
    }
    .image-list {

    }
    .image-upload {
        height: 120px;
        width: 200px;
        float: left;
        margin-right: 10px;
    }
    .image-item {
        height: 120px;
        width: 200px;
        float: left;
        margin-right: 10px;
        border-radius: 6px;
    }
    .upload-icon {
        line-height: 120px;
        position: relative;
        top: -10px;
    }
    .image-item .el-image-viewer__close{
        color: #F0F5FA;
    }
    .image-item i {
        color: red;
        position: relative;
        top: -125px;
        right: -180px;
        z-index: 9;
    }
    .image-body {
        height: 120px;
        width: 200px;

    }
</style>