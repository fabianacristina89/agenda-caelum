package cadastro.caelum.com.br.cadastrocaelum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import modelo.cadastro.caelum.com.br.cadastrocaelum.Aluno;

public class FormularioHelper {


    private EditText editTextNome;
    private EditText editTextTelefone;
    private EditText editTextEndereco;
    private EditText editTextSite;
    private SeekBar seekBarNota;
    private ImageView foto;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        editTextNome = (EditText) activity.findViewById(R.id.nome);
        editTextTelefone = (EditText) activity.findViewById(R.id.telefone);
        editTextEndereco = (EditText) activity.findViewById(R.id.endereco);
        editTextSite = (EditText) activity.findViewById(R.id.site);
        seekBarNota = (SeekBar) activity.findViewById(R.id.nota);
        foto = (ImageView) activity.findViewById(R.id.foto);
        aluno = new Aluno();
    }

    public Aluno pegaAlunoDoFormulario(View view) {

        aluno.setNome(editTextNome.getText().toString());
        aluno.setTelefone(editTextTelefone.getText().toString());
        aluno.setEndereco(editTextEndereco.getText().toString());
        aluno.setSite(editTextSite.getText().toString());
        aluno.setNota((double) seekBarNota.getProgress());

        return aluno;
    }

    public void colocaNoFormulario(Aluno aluno) {
        editTextNome.setText(aluno.getNome());
        editTextEndereco.setText(aluno.getEndereco());
        editTextSite.setText(aluno.getSite());
        editTextTelefone.setText(aluno.getTelefone());
        seekBarNota.setProgress(aluno.getNota().intValue());
        if(aluno.getCaminhoFoto() != null){
            carregaImagem(aluno.getCaminhoFoto());
        }
        this.aluno = aluno;

    }

    public ImageView getFoto() {
        return foto;
    }

    public void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, 100, 100, true);
        aluno.setCaminhoFoto(localArquivoFoto);
        this.foto.setImageBitmap(imagemFotoReduzida);
    }
}