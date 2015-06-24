package adapter.cadastro.caelum.com.br.cadastrocaelum;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cadastro.caelum.com.br.cadastrocaelum.ListaAlunosActivity;
import cadastro.caelum.com.br.cadastrocaelum.R;
import modelo.cadastro.caelum.com.br.cadastrocaelum.Aluno;

/**
 * Created by IT-CPS on 23/06/2015.
 */
public class ListaAlunosAdapter extends BaseAdapter{


    private Activity activity;
    private List<Aluno> lista;

    public ListaAlunosAdapter(Activity activity, List<Aluno> lista) {

        this.activity = activity;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, null);
        ImageView foto = (ImageView) view.findViewById(R.id.foto);
        TextView nome = (TextView) view.findViewById(R.id.nome);
        Aluno aluno = lista.get(position);
        nome.setText(aluno.getNome());

        preencherFoto(foto, aluno);
        if (position % 2 == 0) {
            view.setBackgroundColor(activity.getResources().
                    getColor(R.color.linha_par));
        }
        return view;
    }

    private void preencherFoto(ImageView foto, Aluno aluno) {
        Bitmap bm;
        if(aluno.getCaminhoFoto()!= null){
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        }else{
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }
        foto.setImageBitmap(Bitmap.createScaledBitmap(bm,100, 100, true));
    }
}
