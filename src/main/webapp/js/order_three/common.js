/**
 * 日期转换工具
 * @param datenum
 * @param num
 * @returns {String}
 */
function numberToDatestr(datenum, num) {
	var ndate = "";
	if (datenum != null && datenum != "" && datenum != "undefined") {
		if (num == 8 && datenum.length >= 10) {
			ndate = datenum.substr(0, 10);
		} else if (num == 10 && datenum.length >= 13) {
			ndate = datenum.substr(0, 13);
		} else if (num == 12 && datenum.length >= 16) {
			ndate = datenum.substr(0, 16);
		} else if (num == 14 && datenum.length >= 19) {
			ndate = datenum.substr(0, 19);
		} else {
			ndate = datenum;
		}
	}
	return ndate;
}