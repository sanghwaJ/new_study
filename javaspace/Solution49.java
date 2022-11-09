// 프로그래머스 - 파일명 정렬
import java.util.*;

public class Solution49 {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        //String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        
        System.out.println(Arrays.toString(solution(files)));   
    }

    public static String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
            @Override
			public int compare(String s1, String s2) {
				String[] file1 = divFunction2(s1);
				String[] file2 = divFunction3(s2);
				
				int headValue = file1[0].compareTo(file2[0]);
				
				if(headValue == 0) {
					int num1 = Integer.parseInt(file1[1]);
					int num2 = Integer.parseInt(file2[1]);
					
					return num1 - num2;
				} else {
					return headValue;
				}
			}
        });

        return files;
    }

    // String 사용
    public static String[] divFunction1(String file) {
        String head = "";
        String number = "";
        String tail = "";

        // char 숫자 판별 => Character.isDigit('1')
        // String 숫자 판별 => isNumeric("1")
        for (int i=0; i<file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                head = file.substring(0, i);
                number = file.substring(i, file.length()-1);
                for (int j=0;j<number.length(); j++) {
                    if (!Character.isDigit(number.charAt(j))) {
                        tail = number.substring(j, number.length()-1);
                        number = number.substring(0, j);
                        break;
                    }
                }
                break;
            }
        }
        String[] result = {head.toLowerCase(), number, tail};
        return result;
    }

    // stringBuffer로 효율 개선
    public static String[] divFunction2(String file) {
        StringBuffer sbf = new StringBuffer(file);
        
        String head = "";
        String number = "";
        String tail = "";

        // char 숫자 판별 => Character.isDigit('1')
        // String 숫자 판별 => isNumeric("1")
        for (int i=0; i<file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                head = sbf.substring(0, i);
                number = sbf.substring(i, sbf.length());
                StringBuffer sbn = new StringBuffer(number);
                for (int j=0;j<number.length(); j++) {
                    if (!Character.isDigit(number.charAt(j))) {
                        tail = sbn.substring(j, sbn.length());
                        number = sbn.substring(0, j);
                        break;
                    }
                }
                break;
            }
        }
        String[] result = {head.toLowerCase(), number, tail};

        return result;
    }

    // StringBuilder로 효율 개선
    public static String[] divFunction3(String file) {
        StringBuilder sbf = new StringBuilder(file);
        
        String head = "";
        String number = "";
        String tail = "";

        // char 숫자 판별 => Character.isDigit('1')
        // String 숫자 판별 => isNumeric("1")
        for (int i=0; i<file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                head = sbf.substring(0, i);
                number = sbf.substring(i, sbf.length());
                StringBuilder sbn = new StringBuilder(number);
                for (int j=0;j<number.length(); j++) {
                    if (!Character.isDigit(number.charAt(j))) {
                        tail = sbn.substring(j, sbn.length());
                        number = sbn.substring(0, j);
                        break;
                    }
                }
                break;
            }
        }
        String[] result = {head.toLowerCase(), number, tail};

        return result;
    }
}
