package cadastro.caelum.com.br.cadastrocaelum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.File;

import modelo.caelum.com.br.cadastrocaelum.Aluno;


/**
 * Created by IT-CPS on 11/06/2015.
 */
public class FormularioActivity extends Activity {
    private static final int RESULTADO_CAMERA = 123;
    FormularioHelper formularioHelper;
    private AlunoDAO alunoDAO;
    private DBHelper dbHelper;
    private  String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);
        formularioHelper = new FormularioHelper(this);
        dbHelper = new DBHelper(this);
        alunoDAO = new AlunoDAO(dbHelper);

        formularioHelper.getFoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localArquivoFoto)));
                startActivityForResult(irParaCamera, RESULTADO_CAMERA);
            }
        });

        Aluno alunoEditar = (Aluno) getIntent().getSerializableExtra(Extra.ALUNO_SELECIONADO);
        Button botao = (Button) findViewById(R.id.botao);
        if(alunoEditar != null){
            botao.setText("Alterar");
            formularioHelper.colocaNoFormulario(alunoEditar);
        }else{
            botao.setText("Salvar");
        }


    }
    public void gravar(View view) {

        Aluno aluno = formularioHelper.pegaAlunoDoFormulario(view);

        if(aluno.getId() == null){
            alunoDAO.insere(aluno);
        }else{
            alunoDAO.altera(aluno);
        }
        dbHelper.close();
        finish();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(RESULTADO_CAMERA == requestCode){
            if(resultCode == Activity.RESULT_OK){
                formularioHelper.carregaImagem(this.localArquivoFoto);
            }else{
                this.localArquivoFoto = null;
            }
        }
    }
}
