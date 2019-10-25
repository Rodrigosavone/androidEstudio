package br.com.telalogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.telalogin.Dominio.Entidades.Cliente;

public class ClienteAdpter extends RecyclerView.Adapter<ClienteAdpter.ViewHolderCliente> {

    private List<Cliente> dados;

    public ClienteAdpter(List<Cliente> dados){
        this.dados = dados;
    }

    @Override
    public ClienteAdpter.ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.lista_clientes, parent, false);

        ViewHolderCliente holderCliente  = new ViewHolderCliente(view, parent.getContext());


        return holderCliente;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdpter.ViewHolderCliente holder, int position) {

        if((dados != null) && (dados.size() > 0)) {

            Cliente cliente = dados.get(position);
            holder.txtNome.setText("Nome: " + cliente.nome);
            holder.txtTelefone.setText("Telefone: " + cliente.telefone);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public TextView txtTelefone;

        public ViewHolderCliente(View itemView, final Context context) {
            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
            txtTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(dados.size()>0) {
                        Cliente cliente = dados.get(getLayoutPosition());
                        Intent intent = new Intent(context, TelaCadastroCliente.class);
                        intent.putExtra("CLIENTE",cliente);
                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });

        }
    }
}
