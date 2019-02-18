package test;

public class Test {
	/*
	 * public static void main(String[] args) { int[][] aar = new int[5][6];
	 * for(int i=0;i<5;i++){ for(int j=0;j<6;j++){ aar[i][j]=i+j;
	 * System.out.println(aar[i][j]); } } }
	 */
	public static void main(String[] args) {
		String weeks1 = ",,,星期一,,,星期二,星,,    dfd    期三,星  期四,星期五,星期六,星期天,星期日,,,,";
		String weeks2 = ",,,1,2   ,3,4, dfdfd ,,, 5,6,7,,,";
		System.err.println(Test.weekConversion1(weeks1));
		System.err.println(Test.weekConversion2(weeks2));
	}

	public static String weekConversion1(String weekStr) {
		return weekStr.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",").replaceAll("(^[,]*)|([,]*$)", "")
				.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3").replaceAll("四", "4")
				.replaceAll("五", "5").replaceAll("六", "6").replaceAll("天", "7");
	}

	public static String weekConversion2(String weekStr) {
		return weekStr.replaceAll("[^1234567,]", "").replaceAll("[,]+", ",").replaceAll("(^[,]*)|([,]*$)", "")
				.replaceAll("1", "星期一").replaceAll("2", "星期二").replaceAll("3", "星期三").replaceAll("4", "星期四")
				.replaceAll("5", "星期五").replaceAll("6", "星期六").replaceAll("7", "星期天");
	}

}