package SJProject.assignment1;

import java.io.IOException;

public interface ICRUD {
    public String[] getCreatData();
    public void addList();
    public int create() throws IOException;
    public void levelWord() throws IOException;
    public void meaning() throws IOException;
    public void listAll();
    public void listLevel(int level);
    public int listFind(String find);
}