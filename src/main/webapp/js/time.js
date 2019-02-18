//格式化日期的表示 
function FormatDate(date, str) {
	str = Replace(str, "yyyy", date.getFullYear());
	str = Replace(str, "MM", GetFullMonth(date));
	str = Replace(str, "dd", GetFullDate(date));
	str = Replace(str, "yy", GetHarfYear(date));
	str = Replace(str, "M", date.getMonth() + 1);
	str = Replace(str, "d", date.getDate());
	return str;
}

//增加天 
function AddDays(date, value) {
	date.setDate(date.getDate() + value);
	return date;
}

//增加月 
function AddMonths(date, value) {
	date.setMonth(date.getMonth() + value);
}

//增加年 
function AddYears(date, value) {
	date.setFullYear(date.getFullYear() + value);
}

//是否为今天 
function IsToday(date) {
	return IsDateEquals(date, new Date());
}

//是否为当月 
function IsThisMonth(date) {
	return IsMonthEquals(date, new Date());
}

//两个日期的年是否相等 
function IsMonthEquals(date1, date2) {
	return date1.getMonth() == date2.getMonth()
			&& date1.getFullYear() == date2.getFullYear();
}

//判断日期是否相等 
function IsDateEquals(date1, date2) {
	return date1.getDate() == date2.getDate()
			&& IsMonthEquals(date1, date2);
}

//返回某个日期对应的月份的天数 
function GetMonthDayCount(date) {
	switch (date.getMonth() + 1) {
	case 1:
	case 3:
	case 5:
	case 7:
	case 8:
	case 10:
	case 12:
		return 31;
	case 4:
	case 6:
	case 9:
	case 11:
		return 30;
	}
	//feb: 
	date = new Date(date);
	var lastd = 28;
	date.setDate(29);
	while (date.getMonth() == 1) {
		lastd++;
		AddDays(date, 1);
	}
	return lastd;
}

//返回两位数的年份 
function GetHarfYear(date) {
	var v = date.getYear();
	if (v > 9)
		return v.toString();
	return "0" + v;
}

//返回月份（修正为两位数） 
function GetFullMonth(date) {
	var v = date.getMonth() + 1;
	if (v > 9)
		return v.toString();
	return "0" + v;
}

//返回日 （修正为两位数） 
function GetFullDate(date) {
	var v = date.getDate();
	if (v > 9)
		return v.toString();
	return "0" + v;
}

//替换字符串 
function Replace(str, from, to) {
	return str.split(from).join(to);
}

//统一日期格式 
function ConvertDate(str) {
	str = (str + "").replace(/^\s*/g, "").replace(/\s*$/g, ""); // 去除前后的空白 
	var d;
	// 20040226 -> 2004-02-26
	if (/^[0-9]{8}$/.test(str)) {
		d = new Date(new Number(str.substr(0, 4)), new Number(str
				.substr(4, 2)) - 1, new Number(str.substr(6, 2)));
		if (d.getTime())
			return d;
	}
	d = new Date(str);
	if (d.getTime())
		return d;
	d = new Date(Replace(str, "-", "/"));
	if (d.getTime())
		return d;
	return null;
}

function addDay(days, n) {
	//函数说明：days日期差，n代表如下含义。 
	var my_date_ago = new Date(new Date() - days * 24 * 60 * 60 * 1000
			* -1);//days天的日期 
	switch (n) {
	case 1:
		//返回年 
		return (my_date_ago.getFullYear());
		break;
	case 2:
		//返回月 
		return (my_date_ago.getMonth() + 1);
		break;
	case 3:
		//返回日 
		return (my_date_ago.getDate());
		break;
	default:
		//返回全部 
		return (my_date_ago.getFullYear() + "-"
				+ (my_date_ago.getMonth() + 1) + "-" + my_date_ago
				.getDate());
		break;
	}
}