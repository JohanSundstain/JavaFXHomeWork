package sample;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;

public class DataBaseLoader {

    private static Table table;
    private static Database db;

    DataBaseLoader() { }

    public static void insert(String name,String modules) throws IOException {
        File dataBase = new File("records.mdb");
        Database db = DatabaseBuilder.open(dataBase);
        table = db.getTable("modules");
        table.addRow(new Object[] {"value",name,modules});
        db.flush();
        db.close();
    }


}
