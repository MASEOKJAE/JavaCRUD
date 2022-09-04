package SJProject.assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordMan {
//    private ArrayList<Word> wordGroup = new ArrayList<>();
    public WordMan() {
        System.out.println("입장 완료!!");
    }

    public void ManStart() throws IOException{
        menuList();
    }

    public int update() throws IOException {
        WordCRUD cre = new WordCRUD();
        int judge = cre.create();

        return judge;
    }

    public int delete() throws IOException {

        return 0;
    }
    public void levelCall() throws IOException {

    }
    // menu 창 출력
    public void menuList() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("*** 영단어 마스터 ***\n");
            System.out.println("********************");
            System.out.println("1. 모든 단어 보기");
            System.out.println("2. 수준별 단어 보기");
            System.out.println("3. 단어 검색");
            System.out.println("4. 단어 추가");
            System.out.println("5. 단어 수정");
            System.out.println("6. 단어 삭제");
            System.out.println("7. 파일 저장");
            System.out.println("0. 나가기");
            System.out.println("********************");
            System.out.print(("=> 원하는 메뉴는? "));

            int menu = Integer.parseInt(bf.readLine()); // select 선택 받음

            if (menu == 0) {
                System.out.println("단어 프로그렘을 종료하겠습니다.");
                break;
            } else if (menu == 1) {
                System.out.println("--------------------");
                WordCRUD all = new WordCRUD();
                all.listAll();
            } else if (menu == 2) {
                System.out.println("--------------------");
                WordCRUD level = new WordCRUD();
                System.out.print("원하는 수준을 입력하세요 -> ");
                int stars = Integer.parseInt(bf.readLine());
                level.listLevel(stars);
            } else if (menu == 3) {

            } else if (menu == 4) {
                int check = update();
                if(check == 1)
                    System.out.println("새 단어가 단어장에 추가되었습니다.");
                else
                    System.out.println("잘못된 접근입니다.");
                System.out.println();
            } else if (menu == 5) {

            } else if (menu == 6) {

            } else if (menu == 7) {

            }
        }

    }
}