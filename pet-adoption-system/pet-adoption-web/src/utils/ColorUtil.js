/**
 * 16进制颜色转换成RGBA
 * @param hex
 * @param opacity
 * @returns {`RGB(${number}, ${number}, ${number},1)`}
 */
export const hexToRgb = function (hex,opacity=1) {
    // 去除#号
    let color = hex.replace("#", "");
    // 分割成红、绿、蓝三部分的16进制字符串
    let red = parseInt(color.substring(0,2), 16);
    let green = parseInt(color.substring(2,4), 16);
    let blue = parseInt(color.substring(4,6), 16);
    return `RGB(${red}, ${green}, ${blue},${opacity})`;
}