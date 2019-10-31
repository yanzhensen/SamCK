/** *** 时间相关 ***** */
// 获取当前时间
function getNowFormatDate() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentdate = date.getFullYear() + "-" + month + "-" + strDate + " "
        + hours + ":" + minutes + ":" + seconds;
    return currentdate;
}
// 日期格式化
function formatDate(strTime) {
    var date = new Date(strTime);
    var fYear = date.getFullYear();
    var fMonth;
    if ((date.getMonth() + 1) <= 9) {
        fMonth = date.getMonth() + 1;
        fMonth = "0" + fMonth;
    } else {
        fMonth = date.getMonth() + 1;
    }
    var fDay;
    if (date.getDate() <= 9) {
        fDay = "0" + date.getDate();
    } else {
        fDay = date.getDate();
    }
    return fYear + "-" + fMonth + "-" + fDay;
}
// 自定义时间格式
function formatTime(time, type) {
    // console.log(time);
    var getTime = '';
    // 格式1：月-日 时:分 01-01 00:00
    if (type == '1') {
        var month = time.split("-")[1];
        var date = time.split("-")[2].split(" ")[0];
        var hour = time.split("-")[2].split(" ")[1].split(":")[0];
        var minute = time.split("-")[2].split(" ")[1].split(":")[1];
        getTime = month + "-" + date + " " + hour + ":" + minute;
    }
    // 格式2：年-月-日 2016-01-01
    if (type == '2') {
        var year = time.split("-")[0];
        var month = time.split("-")[1];
        var date = time.split("-")[2].split(" ")[0];
        getTime = year + "-" + month + "-" + date;
    }
    // 格式3：年月日时分秒 201601010000
    if (type == '3') {
        var year = time.split("-")[0];
        var month = time.split("-")[1];
        var date = time.split("-")[2].split(" ")[0];
        var hour = time.split("-")[2].split(" ")[1].split(":")[0];
        var minute = time.split("-")[2].split(" ")[1].split(":")[1];
        var second = time.split("-")[2].split(" ")[1].split(":")[2];
        getTime = year + month + date + hour + minute + second;
    }

    if (type == '4') {
        var year = time.split("-")[0];
        var month = time.split("-")[1];
        var date = time.split("-")[2].split(" ")[0];
        var hour = time.split("-")[2].split(" ")[1].split(":")[0];
        var minute = time.split("-")[2].split(" ")[1].split(":")[1];
        var second = time.split("-")[2].split(" ")[1].split(":")[2];
        getTime = year +"年"+ month+"月" + date+"日 " + hour +":"+ minute ;
    }

    if (type == '5') {
        var year = time.split("-")[0];
        var month = time.split("-")[1];
        var date = time.split("-")[2].split(" ")[0];
        var hour = time.split("-")[2].split(" ")[1].split(":")[0];
        var minute = time.split("-")[2].split(" ")[1].split(":")[1];
        var second = time.split("-")[2].split(" ")[1].split(":")[2];
        getTime = year +"年"+ month+"月" + date+"日 " + hour +":"+ minute+":"+second;
    }


    return getTime;
}
//计算两个日期之间，每自然年的总天数
function getEveryYearTotalDays(beginDate, endDate) {
    var y = 0,//两个日期间相隔的年份
        everyYearBeginArr = [],//每年的开始
        everyYearEndArr = [],//每年的结束
        everyYearTotalDays = [],//每年的总天数
        date,
        begin = new Date(beginDate),
        end = new Date(endDate);
    if (begin >= end) {
        return everyYearTotalDays;
    }
    //计算年份
    do {
        date = new Date(beginDate);
        date.setFullYear(date.getFullYear() + (++y));
        date.setDate(date.getDate() - 1);
    } while (date < end);
//	console.log(beginDate + ' ~ ' + endDate + '之间相隔' + y + '年')
    //计算每年的开始
    for(var i=0;i<y;i++){
        var everyYearBegin = new Date(beginDate);
        everyYearBegin.setFullYear(everyYearBegin.getFullYear() + i);
        everyYearBeginArr.push(everyYearBegin);
    }
    //计算每年的结束
    for(var i=1;i<=y;i++){
        var everyYearEnd = new Date(beginDate);
        everyYearEnd.setFullYear(everyYearEnd.getFullYear() + i);
        everyYearEnd.setDate(everyYearEnd.getDate() - 1);
        if(i<y){
            everyYearEndArr.push(everyYearEnd);
        }else{//最后一年
            everyYearEndArr.push(end);
        }
    }
    //计算每年的总天数
    for(var i=0;i<y;i++){
//		console.log(everyYearBeginArr[i]+' ~ '+everyYearEndArr[i]);
        var thisYearDays = ((everyYearEndArr[i].getTime() - everyYearBeginArr[i].getTime()) / (1000 * 3600 * 24)) + 1;
        everyYearTotalDays.push(thisYearDays);
    }
    return everyYearTotalDays;
}
// 计算任务耗时
function taskCostTime(t1,t2){
    var costTime = '';
    t2 = t2=="" ? getNowFormatDate() : t2;

    t1 = new Date(t1);
    t2 = new Date(t2);
    t1 = t1.getTime();
    t2 = t2.getTime();
    var subTime = parseInt((t2-t1)/1000/60);
    if(subTime<60){
        costTime = subTime+"分";
        return costTime;
    }

    if((subTime/60)>=1 && (subTime/60/24)<1){
        if(subTime%60>0){
            costTime = (subTime/60).toString().split(".")[0]+"小时"+(subTime%60)+"分";
        }else{
            costTime = (subTime/60)+"小时";
        }
        return costTime;
    }
    if((subTime/60/24)>=1){
        if(parseInt(subTime/60)%24>=1){
            costTime = (subTime/(60*24)).toString().split(".")[0]+"天"+(parseInt(subTime/60)%24)+"小时";
        }else{
            costTime = parseInt(subTime/(60*24))+"天";
        }
        return costTime;
    }
}
//计算两个日期之间有几年几月几日
function getYearMonthDay(beginDate, endDate) {
    var y = 0,
        m = 0,
        d = 0,
        date,
        begin = new Date(beginDate),
        end = new Date(endDate);
    if (begin >= end) {
        return [0, 0, 0];
    }
    do {
        date = new Date(beginDate);
        date.setFullYear(date.getFullYear() + (++y));
        date.setDate(date.getDate() - 1);
    } while (date <= end);
    y--;
    do {
        date = new Date(beginDate);
        date.setFullYear(date.getFullYear() + y);
        date.setMonth(date.getMonth() + (++m));
        date.setDate(date.getDate() - 1);
    } while (date <= end);
    m--;
    do {
        date = new Date(beginDate);
        date.setFullYear(date.getFullYear() + y);
        date.setMonth(date.getMonth() + m);
        date.setDate(date.getDate() + (++d));
        date.setDate(date.getDate() - 1);
    } while (date <= end);
    d--;
    return [y, m, d];
}
//计算两个日期相差多少天小时分
function getDays(date1 , date2){
    var dateTime1 = new Date(date1).format('yyyy-MM-dd hh:mm:ss');
    var dateTime1 = new Date(dateTime1.replace(new RegExp("-","gm"),"/")).getTime();
    var dateTime2 = new Date(date2).format('yyyy-MM-dd hh:mm:ss');
    var dateTime2 = new Date(dateTime2.replace(new RegExp("-","gm"),"/")).getTime();
    //时间差的毫秒数
    var date3 = dateTime1 - dateTime2;
    //计算出相差天数
    var days=Math.floor(date3/(24*3600*1000));
    //计算出小时数
    var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
    var hours=Math.floor(leave1/(3600*1000));
    //计算相差分钟数
    var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
    var minutes=Math.floor(leave2/(60*1000));

    return days+'天 '+hours+'时 '+minutes+'分';
}

//获取两个日期的天数差值
function getDayDiff(startDate, endDate){
    var time1 = new Date(startDate).getTime();
    var time2 = new Date(endDate).getTime();
    var days = (time2 - time1) / (1000 * 3600 * 24);
    return days;
}
//日期相加年/月/日
function AddDecDate(date, type, num) {
    var begin = new Date(date);
    if (type == 1) {
        begin.setDate(begin.getDate() + num - 1);
    } else if (type == 2) {
        begin.setMonth(begin.getMonth() + num);
        begin.setDate(begin.getDate() - 1);
    } else if (type == 3) {
        begin.setFullYear(begin.getFullYear() + num);
        begin.setDate(begin.getDate() - 1);
    }
    return formatDate(begin);
}


