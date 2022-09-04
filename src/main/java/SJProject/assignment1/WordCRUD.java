package SJProject.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordCRUD{
    private static ArrayList<Word> wordGroup = new ArrayList<>();
    private String[] creatData = new String[3];

    public WordCRUD() {
        System.out.println("WordCRUD에 입장!!");
    }

    public String[] getCreatData() {
        return creatData;
    }
    // Word class의 객체 형태로 단어들 하나씩 저장
    public void addList() {
        String[] data = new String[3]; // 3가지 데이터 저장
        data = getCreatData();
        Word wData = new Word(Integer.parseInt(data[0]), data[1], data[2]); // Word Class에 단어 정보 객체로 저장
        wordGroup.add(wData); // word 정보 입력 순서대로 객체로 저장
    }
    // 단어 생성
    public int create() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("난이도(1,2,3) & 새 단어 입력 : ");
        levelWord(); // level과 word를 입력 받음
        addList();   // 현재 입력한 정보를 arrayList에 저장

        return 1;
    }
    // 단어 level과 단어 입력
    public void levelWord() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String newLW = bf.readLine();
        String[] temp = newLW.split(" "); // level과 word 분리
        creatData[0] = temp[0]; // level 저장
        creatData[1] = temp[1]; // word 저장
        meaning();
    }
    // 단어 뜻 입력
    public void meaning() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("뜻 입력 : ");
        String newMeaning = bf.readLine();
        creatData[2] = newMeaning;
    }
    // 단어장에 담긴 모든 정보 출력
    public void listAll() {
        for(int i=0; i<wordGroup.size(); i++) {
            int stars = wordGroup.get(i).getLevel();
            if(stars == 1)
                System.out.print("*    ");
            else if(stars == 2)
                System.out.print("**   ");
            else if(stars == 3)
                System.out.print("***  ");
            System.out.print(wordGroup.get(i).getWord() + "   ");
            System.out.println(wordGroup.get(i).getDef());
        }
        System.out.println();
    }
    // 지정한 난이도만 필터링 해서 단어장 정보 출력
    public void listLevel(int level) {
        for(int i=0; i<wordGroup.size(); i++) {
            int stars = wordGroup.get(i).getLevel();
            if(level == stars) { // 현재 순서의 단어 레벨과 필터링 레벨이 같을 경우
                for(int j=0; j<level; j++) // 지정된 레벨만큼 별 개수 출력
                    System.out.print("*");
                System.out.print("   " + wordGroup.get(i).getWord() + "   ");
                System.out.println(wordGroup.get(i).getDef());
            }
        }
    }
}