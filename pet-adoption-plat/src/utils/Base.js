import { baseURL } from "@/utils/Request.js";
export const splicingDomain = function (urlList) {
    if (!urlList) return;
    urlList = urlList.map(x => { return baseURL() + x})
    return urlList;
}

export const dbImages = function (urlList) {
    if (!urlList) return;
    urlList = urlList.map(x => { return x.replace(baseURL(),"")})
    return urlList;
}

