package br.edu.ifspsaocarlos.agenda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;

import java.util.List;


public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private List<Contato> contatos;
    private Context context;

    private static ItemClickListener clickListener;
    private static FavoritoClickListener favoritoClickListener;


    public ContatoAdapter(List<Contato> contatos, Context context) {
        this.contatos = contatos;
        this.context = context;
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contato_celula, parent, false);
        return new ContatoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContatoViewHolder holder, int position) {
        Contato contato = contatos.get(position);
        holder.nome.setText(contato.getNome());
        if (contato.getFavorito() == 0)
            holder.favorito.setImageResource(R.drawable.icone_favorito_off);
        else
            holder.favorito.setImageResource(R.drawable.icone_favorito_on);
    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

    public void setFavoritoClickListener(FavoritoClickListener itemClickListener) {
        favoritoClickListener = itemClickListener;
    }


    public class ContatoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nome;
        final ImageView favorito;

        ContatoViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.nome);
            favorito = (ImageView) view.findViewById(R.id.iv_Favorito);
            view.setOnClickListener(this);
            favorito.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.iv_Favorito) {
                if (favoritoClickListener != null)
                    favoritoClickListener.onItemClick(getAdapterPosition());

            } else {

                if (clickListener != null)
                    clickListener.onItemClick(getAdapterPosition());
            }
        }
    }


    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public interface FavoritoClickListener {
        void onItemClick(int position);
    }

}


