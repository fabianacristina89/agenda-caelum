package cadastro.caelum.com.br.cadastrocaelum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import adapter.cadastro.caelum.com.br.cadastrocaelum.ListaAlunosAdapter;
import modelo.cadastro.caelum.com.br.cadastrocaelum.Aluno;

/**
 * Created by IT-CPS on 11/06/2015.
 */
public class ListaAlunosActivity extends AppCompatActivity {

    private AlunoDAO alunoDAO;
    private DBHelper dbHelper;
    private ListView listaAlunos;
    private Aluno alunoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this);
        alunoDAO = new AlunoDAO(dbHelper);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);
        listaAlunos = (ListView) findViewById(R.id.lista);
        registerForContextMenu(listaAlunos);

        listaAlunos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                alunoSelecionado = (Aluno) adapter.getItemAtPosition(position);
                Intent irParaFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                irParaFormulario.putExtra(Extra.ALUNO_SELECIONADO, alunoSelecionado);
                startActivity(irParaFormulario);
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                alunoSelecionado = (Aluno) adapter.getItemAtPosition(position);
                return false;
            }
        });

    }

    private void carregaLista() {
        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this, alunoDAO.getLista());
        listaAlunos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_novo:
                Intent irParaFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(irParaFormulario);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        carregaLista();


        super.onResume();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Ligar").setIntent(
                new Intent(Intent.ACTION_CALL)
                        .setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()))
        );
        menu.add("Enviar SMS").setIntent(
                new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()))
                        .putExtra("sms_body", "Sua nota: " + alunoSelecionado.getNota())
        );
        menu.add("Achar Mapa").setIntent(
                new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("geo:0,0?z=14&q=" + alunoSelecionado.getEndereco()))
        );
        menu.add("Navegar no site").setIntent(
                new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(getSiteComPrefixo()))
        );
        menu.add("Deletar").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                alunoDAO.deletar(alunoSelecionado);
                dbHelper.close();
                carregaLista();
                return false;
            }
        });
        menu.add("Enviar E-mail").setIntent(
                new Intent(Intent.ACTION_SEND)
                        .setType("message/rtc822")
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{alunoSelecionado.getEmail()})
                        .putExtra(Intent.EXTRA_SUBJECT, "testando assunto")
                        .putExtra(Intent.EXTRA_TEXT, "cORPO DO EMAIL")
        );
        super.onCreateContextMenu(menu, v, menuInfo);


    }

    private String getSiteComPrefixo() {
        return alunoSelecionado.getSite().startsWith("http:") ? alunoSelecionado.getSite() : "http:" + alunoSelecionado.getSite();
    }
}
