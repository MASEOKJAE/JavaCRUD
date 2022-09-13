package SJProject.assignment1;

import java.io.*;
import java.util.ArrayList;

public class WordCRUD implements ICRUD {
    private static ArrayList<Word> wordGroup = new ArrayList<>(); // 단어들을 관리할 단어장
    private String[] createData; // 단어 새로 생성 시, [0] = 난이도 / [1] = 단어 이름 / [2] = 단어 의미

    public WordCRUD() {
        createData = new String[3];
    }

    public String[] getCreatData() {
        return createData;
    }

    // Word class의 객체로 단어들을 관리
    public void addList() {
        String[] data = new String[3]; // 3가지 데이터 저장
        data = getCreatData();
        Word wData = new Word(Integer.parseInt(data[0]), data[1], data[2]); // Word Class에 단어 정보 객체로 저장
        wordGroup.add(wData); // word 정보 입력 순서대로 객체로 저장
    }

    // 새로운 단어 추가
    public int create() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("난이도(1,2,3) & 새 단어 입력 : ");
        levelWord(); // level과 word를 입력 받음
        addList();   // 현재 입력한 정보를 arrayList에 저장

        return 1;
    }

    public void levelWord() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String newLW = bf.readLine();
        String[] temp = newLW.split(" "); // level과 word 분리
        createData[0] = temp[0]; // level 저장
        createData[1] = temp[1]; // word 저장
        meaning();
    }

    public void meaning() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("뜻 입력 : ");
        String newMeaning = bf.readLine();
        createData[2] = newMeaning;
    }

    // 단어장에 있는 모든 단어들
    public void listAll() {
        for (int i = 0; i < wordGroup.size(); i++) {
            int stars = wordGroup.get(i).getLevel();
            if (stars == 1)
                System.out.print("*    ");
            else if (stars == 2)
                System.out.print("**   ");
            else if (stars == 3)
                System.out.print("***  ");
            System.out.print(wordGroup.get(i).getWord() + "   ");
            System.out.println(wordGroup.get(i).getDef());
        }
    }

    // 선택한 수준으로 필터링 한 단어들
    public void listLevel(int level) {
        for (int i = 0; i < wordGroup.size(); i++) {
            int stars = wordGroup.get(i).getLevel();
            if (stars == level) { // 선택한 수준과 현재 순서의 단어의 수준이 같음
                for (int j = 0; j < level; j++) // 수준만큼 *을 출력
                    System.out.print("*");
                System.out.print("    " + wordGroup.get(i).getWord() + "   ");
                System.out.println(wordGroup.get(i).getDef());
            }
        }
    }

    // 검색한 단어를 찾는 메소드
    public int listFind(String find) {
        int findCheck = 0; // 검색한 단어를 찾으면 1로 값을 변
        for (int i = 0; i < wordGroup.size(); i++) {
            String wordNow = wordGroup.get(i).getWord();
            if (wordNow.toUpperCase().contains(find)) { // 검색한 단어와 현재 단어가 같음
                for (int j = 0; j < wordGroup.get(i).getLevel(); j++) // 수준만큼 *을 출력
                    System.out.print("*");
                System.out.print("    " + wordNow + "   ");
                System.out.println(wordGroup.get(i).getDef());
                findCheck = 1; // 단어를 발견했으니 1로 변경
            }
        }
        return findCheck;
    }

    public void modifyManager(String find) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int findCheck = 0, num = 1; // 검색한 단어를 찾으면 1로 값을 변환, 검색된 단어 순서
        System.out.println("--------------------");
        for (int i = 0; i < wordGroup.size(); i++) {
            String wordNow = wordGroup.get(i).getWord();
            if (wordNow.toUpperCase().contains(find)) { // 검색한 단어와 현재 단어가 같음
                System.out.print(num + " "); // 저장된 순서대로 번호 출력
                for (int j = 0; j < wordGroup.get(i).getLevel(); j++) // 수준만큼 *을 출력
                    System.out.print("*");
                System.out.print("    " + wordNow + "   ");
                System.out.println(wordGroup.get(i).getDef());
                findCheck = 1; // 단어를 발견했으니 1로 변경
                num++;
            }
        }
        System.out.println("--------------------");
        if(findCheck == 1) { // 검색한 단어가 1개 이상 발견
            System.out.print("수정할 번호 선택 -> ");
            int modifyNum = Integer.parseInt(bf.readLine());

            modifyWorker(modifyNum, find); // 실질적으로 단어를 수정하는 함수 호출
        } else // 검색한 단어와 일치하는 정보가 없음
            System.out.println("검색하신 단어가 존재하지 않습니다.\n");
    }

    // 단어 정보를 실질적으로 삭제하는 작업자
    public void modifyWorker(int location, String find) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = 1; // 검색된 단어 순서
        for(int i = 0; i < wordGroup.size(); i++) {
            String wordNow = wordGroup.get(i).getWord();
            if (wordNow.toUpperCase().contains(find)) { // 검색한 단어와 현재 단어가 같음
                if(num == location) {
                    System.out.print("수정할 뜻 입력: ");
                    String mdMean = bf.readLine(); // 수정할 뜻 mdMean에 입력
                    wordGroup.get(i).setDef(mdMean); // 선택한 번호에 해당하는 단어 수정
                    System.out.println("선택한 단어 수정이 완료되었습니다.\n");
                    break;
                }
                else
                    num++;
            }
        }
    }

    // 단어 정보 삭제를 지시하는 메니저
    public void deleteManager(String find) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int findCheck = 0, num = 1; // 검색한 단어를 찾으면 1로 값을 변환, 검색된 단어 순서
        System.out.println("--------------------");
        for (int i = 0; i < wordGroup.size(); i++) {
            String wordNow = wordGroup.get(i).getWord();
            if (wordNow.toUpperCase().contains(find)) { // 검색한 단어와 현재 단어가 같음
                System.out.print(num + " "); // 저장된 순서대로 번호 출력
                for (int j = 0; j < wordGroup.get(i).getLevel(); j++) // 수준만큼 *을 출력
                    System.out.print("*");
                System.out.print("    " + wordNow + "   ");
                System.out.println(wordGroup.get(i).getDef());
                findCheck = 1; // 단어를 발견했으니 1로 변경
                num++;
            }
        }
        System.out.println("--------------------");
        if(findCheck == 1) { // 검색한 단어가 1개 이상 발견
            System.out.print("삭제할 번호 선택 -> ");
            int deleteNum = Integer.parseInt(bf.readLine());
            System.out.print(("정말로 삭제하실래요?(Y/N) -> "));
            String confirm = bf.readLine();
            deleteWorker(deleteNum, find, confirm.toUpperCase()); // 실질적으로 단어를 삭제하는 함수 호출
        } else // 검색한 단어와 일치하는 정보가 없음
            System.out.println("검색하신 단어가 존재하지 않습니다.\n");
    }

    // 단어 정보를 실질적으로 삭제하는 작업자
    public void deleteWorker(int location, String find, String confirm) {
        int num = 1; // 검색된 단어 순서
        if(confirm.equals("Y")) { // 단어 삭제를 희망
            for (int i = 0; i < wordGroup.size(); i++) {
                String wordNow = wordGroup.get(i).getWord();
                if (wordNow.toUpperCase().contains(find)) { // 검색한 단어와 현재 단어가 같음
                    if(num == location) {
                        wordGroup.remove(i); // 선택한 번호에 해당하는 단어 삭제
                        System.out.println("선택한 단어 삭제가 완료되었습니다.\n");
                        break;
                    }
                    else
                        num++;
                }
            }
        } else // 단어 삭제 철회
            System.out.println("선택한 단어 삭제를 취소하셨습니다.\n");
    }

    public void saveList() {
        File file = new File("wordList.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<wordGroup.size(); i++) {
            try {
                writer.write(wordGroup.get(i).getLevel());
                writer.write("|");
                writer.write(wordGroup.get(i).getWord());
                writer.write("|");
                writer.write(wordGroup.get(i).getDef());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
