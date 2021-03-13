package sample.fileloader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    private FileReader reader;
    private String buffer;
    private String[] res;
    private int number;
    private String name, modules;
    private ArrayList<FileLoader> listJava,listFxml;

    public FileLoader(String file) {
        this.buffer = "";
        listFxml = new ArrayList<FileLoader>();
        listJava = new ArrayList<FileLoader>();
        File opened = new File(file);
        res = opened.list();
        int cJava = 1, cFxml = 1;
        for (int i = 0; i < res.length; i++){
            String[] extension = res[i].split("\\.");
            if (extension.length > 1){
                if (extension[extension.length-1].endsWith("java")) {
                    this.number = cJava++;
                    this.name = res[i];
                    try(FileReader reader = new FileReader(file + "\\" + res[i])) {
                        int c;
                        while((c=reader.read())!=-1){
                            buffer += (char)c;
                        }
                    }
                    catch(IOException ex){

                        System.out.println(ex.getMessage());
                    }
                    this.modules = pars(buffer);
                    listJava.add(new FileLoader(this.number,this.name,this.modules));
                }
                else{
                    this.number = cFxml++;
                    this.name = res[i];
                    try(FileReader reader = new FileReader(file + "\\" + res[i])) {
                        int c;
                        while((c=reader.read())!=-1){
                            buffer += (char)c;
                        }
                    }
                    catch(IOException ex){

                        System.out.println(ex.getMessage());
                    }
                    this.modules = pars(buffer);
                    listFxml.add(new FileLoader(this.number,this.name,this.modules));
                }
            }
        }
    }

    public FileLoader(int number,String name, String modules){
        this.number = number;
        this.name = name;
        this.modules = modules;
    }

    public FileLoader() { }

    public String pars(String buffer) {
        String[] r = buffer.split("\\s*(\\s|\\?|<|>|:|;|,|\\+|=|-|\\|\\*|\\{|\\}|\\(|\\))\\s*");
        String str = "";
        for (int i = 0; i < r.length; i++) {
            if (r[i].endsWith("import")) {
                str += r[++i] + "\n";
            }
        }
        return str;
    }

    public ArrayList<FileLoader> getList(boolean f) {
        if (f){
            return  listJava;
        }
        return listFxml;

    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getModules() {
        return modules;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setModules(String modules){
        this.modules = modules;
    }
}
