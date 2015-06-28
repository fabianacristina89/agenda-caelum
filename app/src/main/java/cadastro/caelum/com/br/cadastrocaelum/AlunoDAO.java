package cadastro.caelum.com.br.cadastrocaelum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import modelo.caelum.com.br.cadastrocaelum.Aluno;


/**
 * Created by IT-CPS on 12/06/2015.
 */
public class AlunoDAO {

    private final DBHelper dbHelper;

    public AlunoDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    public void insere(Aluno aluno){
        dbHelper.getWritableDatabase().insert(DBHelper.TABELA_ALUNOS, null, toValues(aluno));
    }
    public void altera(Aluno aluno) {
        String[] args = {aluno.getId().toString()};
        dbHelper.getWritableDatabase().update(DBHelper.TABELA_ALUNOS, toValues(aluno), "id = ?", args);

    }
    private ContentValues toValues(Aluno aluno) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", aluno.getNome());
        contentValues.put("telefone", aluno.getTelefone());
        contentValues.put("endereco", aluno.getEndereco());
        contentValues.put("site", aluno.getSite());
        contentValues.put("nota", aluno.getNota());
        contentValues.put("caminhoFoto", aluno.getCaminhoFoto());
        return contentValues;
    }

    public List<Aluno> getLista(){
        String sql = "select * from " + DBHelper.TABELA_ALUNOS;
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<>();
        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            alunos.add(aluno);
        }
        return alunos;

    }

    public void deletar(Aluno aluno){
        String[] args = {aluno.getId().toString()};
        dbHelper.getWritableDatabase().delete(DBHelper.TABELA_ALUNOS, "id = ? ", args);
    }
    public boolean isAluno(String telefone) {

        Cursor rawQuery = dbHelper.getReadableDatabase().rawQuery("SELECT telefone from " + DBHelper.TABELA_ALUNOS
                + " WHERE telefone = ?", new String[]{telefone});

        boolean existe = rawQuery.moveToFirst();
        rawQuery.close();

        return existe;
    }


}
