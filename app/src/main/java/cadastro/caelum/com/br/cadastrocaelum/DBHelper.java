package cadastro.caelum.com.br.cadastrocaelum;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IT-CPS on 15/06/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "CadastroCaelum";
    public static final int VERSION = 1;
    public static final String TABELA_ALUNOS = "Alunos";

    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE "+ TABELA_ALUNOS +" (id INTEGER PRIMARY KEY, " +
                " nome TEXT UNIQUE NOT NULL, " +
                " telefone TEXT, " +
                " endereco TEXT," +
                " site TEXT, " +
                " nota REAL, " +
                " caminhoFoto TEXT);";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_ALUNOS;
        db.execSQL(sql);
        onCreate(db);
    }


}
