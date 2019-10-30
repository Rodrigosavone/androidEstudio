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

import br.com.telalogin.Dominio.Entidades.CategoriaLivro;
import br.com.telalogin.Dominio.Entidades.Leitor;

public class CategoriaLirvoAdapter extends RecyclerView.Adapter<CategoriaLirvoAdapter.ViewHolderCategoriaLivro> {

    private List<CategoriaLivro> dados;

    public CategoriaLirvoAdapter(List<CategoriaLivro> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public CategoriaLirvoAdapter.ViewHolderCategoriaLivro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.lista_categoria_livros, parent, false);

        ViewHolderCategoriaLivro holderCatLivro = new ViewHolderCategoriaLivro(view, parent.getContext());


        return holderCatLivro;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaLirvoAdapter.ViewHolderCategoriaLivro holder, int position) {
        if((dados != null) && (dados.size() > 0)) {

            CategoriaLivro categoriaLivro = dados.get(position);
            holder.txtCdCategoria.setText("Código da categoria: " + categoriaLivro.cdCategoria);
            holder.txtDsCategoria.setText("Descrição: " + categoriaLivro.dsCategoria);
            holder.txtNumMaxDias.setText("Dias permitidos para emprestar a obra: " + categoriaLivro.numMaxDias + " dias");
            holder.txtTaxaMulta.setText("Taxa de multa: " + categoriaLivro.taxaMulta + " %");
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderCategoriaLivro extends RecyclerView.ViewHolder{

        public TextView txtCdCategoria;
        public TextView txtDsCategoria;
        public TextView txtNumMaxDias;
        public TextView txtTaxaMulta;

        public ViewHolderCategoriaLivro(View itemView, final Context context) {
            super(itemView);
            txtCdCategoria = (TextView) itemView.findViewById(R.id.txtCdCategoria);
            txtDsCategoria = (TextView) itemView.findViewById(R.id.txtDsCategoria);
            txtNumMaxDias = (TextView) itemView.findViewById(R.id.txtNumMaxDias);
            txtTaxaMulta = (TextView) itemView.findViewById(R.id.txtTaxaMulta);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(dados.size()>0) {
                        CategoriaLivro categoriaLivro  = dados.get(getLayoutPosition());
                        Intent intent = new Intent(context, TelaCadastroCatLivro.class);
                        intent.putExtra("CATEGORIALIVRO", categoriaLivro);
                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
