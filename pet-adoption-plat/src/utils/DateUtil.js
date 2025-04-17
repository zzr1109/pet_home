export const dateFormatHms = function(originVal) {
    const dt = new Date(originVal);

    const y = dt.getFullYear();
    const m = (dt.getMonth() + 1 + "").padStart(2, "0");
    const d = (dt.getDate() + "").padStart(2, "0");

    const hh = (dt.getHours() + "").padStart(2, "0");
    const mm = (dt.getMinutes() + "").padStart(2, "0");
    const ss = (dt.getSeconds() + "").padStart(2, "0");

    return `${y}-${m}-${d} ${hh}:${mm}:${ss}`;
}

export const dateFormatHm = function(originVal) {
    const dt = new Date(originVal);

    const y = dt.getFullYear();
    const m = (dt.getMonth() + 1 + "").padStart(2, "0");
    const d = (dt.getDate() + "").padStart(2, "0");

    const hh = (dt.getHours() + "").padStart(2, "0");
    const mm = (dt.getMinutes() + "").padStart(2, "0");
    const ss = (dt.getSeconds() + "").padStart(2, "0");

    return `${y}-${m}-${d} ${hh}:${mm}`;
}

export const dateFormat = function(originVal) {
    const dt = new Date(originVal);

    const y = dt.getFullYear();
    const m = (dt.getMonth() + 1 + "").padStart(2, "0");
    const d = (dt.getDate() + "").padStart(2, "0");

    const hh = (dt.getHours() + "").padStart(2, "0");
    const mm = (dt.getMinutes() + "").padStart(2, "0");
    const ss = (dt.getSeconds() + "").padStart(2, "0");

    return `${y}-${m}-${d}`;
}

/**
 * 获取俩个时间戳相差天数
 * @param startDateTimeStamp
 * @param endDateTimeStamp
 * @returns {number}
 */
export const getTotalDaysByDateRange = function (startDateTimeStamp, endDateTimeStamp){

    //包含开始日期 结束日期在内的总天数
    let total = Math.ceil(Math.abs(startDateTimeStamp - endDateTimeStamp) / 86400000) + 1
    return total
}
