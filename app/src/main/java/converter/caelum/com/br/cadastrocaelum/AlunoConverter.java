package converter.caelum.com.br.cadastrocaelum;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import modelo.caelum.com.br.cadastrocaelum.Aluno;


public class AlunoConverter {

    public String toJSON(List<Aluno> alunos) {
        JSONStringer jsonStringer = new JSONStringer();
        String json = "";

        try {
            jsonStringer.object().key("list").array().
                    object().key("aluno").array();


            for (Aluno aluno : alunos) {
                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("endereco").value(aluno.getEndereco())
                        .key("site").value(aluno.getSite())
                        .key("nota").value(aluno.getNota())
                        .endObject();
            }

            json = jsonStringer.endArray()
                    .endObject().endArray().endObject().toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
