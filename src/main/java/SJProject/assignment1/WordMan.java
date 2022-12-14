package SJProject.assignment1;

import java.io.*;
import java.util.ArrayList;

public class WordMan {
    public WordMan() {
    }
    // 파일 내 단어와 메뉴 리스트로 접근하게 해주는 메소드
    public void ManStart() throws IOException {
        loadfile();
        menuList();
    }
    // "wordList.txt" 파일 내 단어 개수와 단어 정보들을 "wordGroup"객체에 세팅
    public void loadfile() throws IOException {
        int wordNum = 0; // 단어 개수 저장
        WordCRUD crud = new WordCRUD(); // WordCRUD 객체 "crud" 선언
        File file = new File("wordList.txt");
        BufferedReader inFile = new BufferedReader(new FileReader(file));

        if(file.exists()) {
            String line = null;
            while( (line = inFile.readLine()) != null ) {
                // 파일 내 단어 정보(수준 / 단어 / 뜻) 저장
                String[] fileInfo = line.split("\\|"); // "|" 정규표현식으로 인지되므로, "\\|" 이스케이프 처리
                // 파일에서 가져온 단어 정보 ArrayList인 wordGroup에 저장
                Word fData = new Word(Integer.parseInt(fileInfo[0]), fileInfo[1], fileInfo[2]); //
                crud.getWordGroup().add(fData); // ArrayList "wordGroup"에 단어 정보 저장
                wordNum++;
            }
        }
        inFile.close();
        System.out.println("=> " + wordNum + "개의 단어 로딩이 완료되었습니다.\n");
    }
    // 새로운 단어를 업데이트하게 해주는 메소드
    public int update(WordCRUD crud) throws IOException {
        int judge = crud.create();

        return judge;
    }
    // menu 창 출력
    public void menuList() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        WordCRUD crud = new WordCRUD(); // wordCRUD 접근을 위한 객체 생성
        System.out.println("*** 영단어 마스터 ***\n");

        while(true) {
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
                System.out.println("단어 프로그램을 종료하겠습니다.");
                break;
            } else if (menu == 1) {
                System.out.println("--------------------");
                crud.listAll();
                System.out.println("--------------------\n");
            } else if (menu == 2) {
                System.out.print("원하는 수준(1:초급, 2:중급, 3:고급)을 입력하세요 -> ");
                int stars = Integer.parseInt(bf.readLine());
                System.out.println("--------------------");
                crud.listLevel(stars);
                System.out.println("--------------------\n");
            } else if (menu == 3) {
                System.out.print("찾고 싶은 단어를 입력하세요 -> ");
                String find = bf.readLine();
                System.out.println("--------------------");
                int check = crud.listFind(find.toUpperCase());
                if(check != 1) // check가 1이 아니면 찾는 단어가 존재하지 않는 것
                    System.out.println("검색하신 단어가 존재하지 않습니다.");
                System.out.println("--------------------\n");
            } else if (menu == 4) {
                int check = update(crud);
                if(check == 1)
                    System.out.println("새 단어가 단어장에 추가되었습니다.");
                else
                    System.out.println("잘못된 접근입니다.");
                System.out.println();
            } else if (menu == 5) {
                System.out.print("수정하고 싶은 단어를 입력하세요 -> ");
                String find = bf.readLine();
                crud.modifyManager(find.toUpperCase());
            } else if (menu == 6) {
                System.out.print("삭제하고 싶은 단어를 입력하세요 -> ");
                String find = bf.readLine();
                crud.deleteManager(find.toUpperCase());
            } else if (menu == 7) {
                crud.saveList();
                System.out.println("\"wordList.txt\" 단어장에 새로운 단어가 저장되었습니다.\n");
            }
        }

    }
}