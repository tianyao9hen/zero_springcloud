/**
 * Created by pxf on 2020/8/4.
 */


//日期格式化，返回值类型为yyyy-mm-dd dd:mm:ss
export function getDate(date) {
    let d = new Date(date)
    if (date == null) {
        d = new Date();
    }
    var m = d.getMonth() + 1;
    if (m < 10) {
        m = "0" + m;
    }
    var day = d.getDate();
    if (day < 10) {
        day = '0' + day;
    }
    var minu = d.getMinutes();
    if (minu < 10) {
        minu = "0" + minu;
    }

    var sources = d.getSeconds();
    if (sources < 10) {
        sources = '0' + sources;
    }
    return d.getFullYear() + '-' + m + '-' + day + " " + d.getHours() + ":" + minu + ":" + sources;
}

export function getLastTimeForDate(date){
    let newDate = getDate(date);
    newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec(newDate) + " 23:59:59";
    return newDate;
}

//获取这周的周一
export function getFirstDayOfWeek (date) {
    var weekday = date.getDay()||7; //获取星期几,getDay()返回值是 0（周日） 到 6（周六） 之间的一个整数。0||7为7，即weekday的值为1-7
    date.setDate(date.getDate()-weekday+1);//往前算（weekday-1）天，年份、月份会自动变化

    let newDate = getDate(date);
    newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec(newDate) + " 00:00:00";
    return newDate;
}

//获取当月第一天
export function getFirstDayOfMonth (date) {
    date.setDate(1);
    let newDate = getDate(date);
    newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec(newDate) + " 00:00:00";
    return newDate;
}

//获取当季第一天
export function getFirstDayOfSeason (date) {
    var month = date.getMonth();
    if(month <3 ){
        date.setMonth(0);
    }else if(2 < month && month < 6){
        date.setMonth(3);
    }else if(5 < month && month < 9){
        date.setMonth(6);
    }else if(8 < month && month < 11){
        date.setMonth(9);
    }
    date.setDate(1);
    let newDate = getDate(date);
    newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec(newDate) + " 00:00:00";
    return newDate;
}

//获取当年第一天
export function getFirstDayOfYear (date) {
    date.setDate(1);
    date.setMonth(0);
    let newDate = getDate(date);
    newDate=/\d{4}-\d{1,2}-\d{1,2}/g.exec(newDate) + " 00:00:00";
    return newDate;
}