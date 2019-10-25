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

import br.com.telalogin.Dominio.Entidades.Leitor;

public class LeitorAdpter extends RecyclerView.Adapter<LeitorAdpter.ViewHolderLeitor>{

    private List<Leitor> dados;

    public LeitorAdpter(List<Leitor> dados){
        this.dados = dados;
    }

    @Override
    public LeitorAdpter.ViewHolderLeitor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.lista_leitores, parent, false);

        ViewHolderLeitor holderLeitor = new ViewHolderLeitor(view, parent.getContext());


        return holderLeitor;
    }


    @Override
    public void onBindViewHolder(@NonNull LeitorAdpter.ViewHolderLeitor holder, int position) {

        if((dados != null) && (dados.size() > 0)) {

            Leitor leitor = dados.get(position);
            holder.txtCdCategoria.setText("Código da categoria: " + leitor.cdCategoria);
            holder.txtDsCategoria.setText("Descrição: " + leitor.dsCategoria);
            holder.txtNumMaxDias.setText("Dias permitidos para emprestar a obra: " + leitor.numMaxDias + " dias");
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderLeitor extends RecyclerView.ViewHolder{

        public TextView txtCdCategoria;
        public TextView txtDsCategoria;
        public TextView txtNumMaxDias;

        public ViewHolderLeitor(View itemView, final Context context) {
            super(itemView);
            txtCdCategoria = (TextView) itemView.findViewById(R.id.txtCdCategoria);
            txtDsCategoria = (TextView) itemView.findViewById(R.id.txtDsCategoria);
            txtNumMaxDias = (TextView) itemView.findViewById(R.id.txtNumMaxDias);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(dados.size()>0) {
                        Leitor leitor  = dados.get(getLayoutPosition());
                        Intent intent = new Intent(context, TelaCadastroCatLeitor.class);
                        intent.putExtra("LEITOR",leitor);
                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });

        }
    }
}
