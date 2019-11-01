package br.com.telalogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import br.com.telalogin.Dominio.Entidades.Livro;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.ViewHolderLivro> {

    private List<Livro> dados;


    public LivroAdapter(List<Livro> dados){
        this.dados = dados;
    }


    @Override
    public LivroAdapter.ViewHolderLivro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.lista_livros, parent, false);

        ViewHolderLivro holderLivro = new ViewHolderLivro(view, parent.getContext());

        return holderLivro;
    }

    @Override
    public void onBindViewHolder(@NonNull LivroAdapter.ViewHolderLivro holder, int position) {
        if((dados != null) && (dados.size() > 0)) {

            Livro livro = dados.get(position);

            holder.txtCodigoLivro.setText("Código do Livro: " + livro.codigoLivro);
            holder.txtTitulo.setText("Titulo do Livro: " + livro.titulo);
            //holder.txtCodCategoria.setText("Categoria do Livro: " + livro.categoria);
            holder.txtAutores.setText("Autor do Livro: " + livro.autor);
            holder.txtEditora.setText("Editora do Livro: " + livro.editora);
            holder.txtNumPagina.setText("Número de Páginas: " + livro.numPagina + " paginas");

        }
    }


    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderLivro extends RecyclerView.ViewHolder{

        public TextView txtCodigoLivro;
        public TextView txtISBN;
        public TextView txtTitulo;
        public TextView txtCodCategoria;
        public TextView txtAutores;
        public TextView txtPalavraChave;
        public TextView txtDtPublicacao;
        public TextView txtnumEdicao;
        public TextView txtEditora;
        public TextView txtNumPagina;

        public ViewHolderLivro(View itemView, final Context context){
            super(itemView);

            txtCodigoLivro = (TextView) itemView.findViewById(R.id.txtCodigoLivro);
            txtISBN = (TextView)itemView.findViewById(R.id.txtISBN);
            txtTitulo = (TextView)itemView.findViewById(R.id.txtTitulo);
            txtCodCategoria = (TextView)itemView.findViewById(R.id.txtCodCategoria);
            txtAutores = (TextView)itemView.findViewById(R.id.txtAutores);
            txtPalavraChave = (TextView)itemView.findViewById(R.id.txtPalavraChave);
            txtDtPublicacao = (TextView)itemView.findViewById(R.id.txtDtPublicacao);
            txtnumEdicao = (TextView)itemView.findViewById(R.id.txtNumEdicao);
            txtEditora = (TextView)itemView.findViewById(R.id.txtEditora);
            txtNumPagina = (TextView)itemView.findViewById(R.id.txtNumPagina);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(dados.size()>0) {
                        Livro livro  = dados.get(getLayoutPosition());
                        Intent intent = new Intent(context, TelaCadastroLivro.class);
                        intent.putExtra("LIVRO",livro);
                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });

        }
    }
}
