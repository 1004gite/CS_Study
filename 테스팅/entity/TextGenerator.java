package entity;

/**
 소프트웨어공학 13주차 실습 1 by Hansae Ju
 1. TextGenerator의 각 메소드에 대해 유닛 테스트 코드를 작성하시오.
 2. halfText() 메소드와 halfText2() 메소드가 동일한지 확인하고, 버그가 있다면 수정하시오.
 3. 프로젝트 전체를 압축하여 .zip 파일로 제출하시오.
 **/
public class TextGenerator {
    public TextGenerator() {

    }

    public String makeText(String origin, int mult) {
        String r = "";
        for (int i = 0; i < mult; i++) {
            r += origin;
        }
        return r;
    }

    public String reverseText(String origin) {
        return new StringBuffer(origin).reverse().toString();
    }

    public String halfText(String origin) {
        return origin.substring(0, origin.length()/2);
    }

    public String halfText2(String origin) {
        String r = "";
        for (int i = 0; i < origin.length()/2; i++) {
            r += origin.charAt(i);
        }
        return r;
    }
}