package com.tarea2.mvs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Adaptador personalizado para mostrar una lista de objetos Personaje en un RecyclerView.
 */
public class AdapterPersonaje extends RecyclerView.Adapter<AdapterPersonaje.PersonajeViewHolder> {

    /** Contexto de la actividad o fragmento que usa el adaptador */
    private Context context;

    /** Lista de personajes que se mostrará en el RecyclerView */
    private List<Personaje> personajeList;

    /** Interfaz para manejar los clics en cada elemento de la lista */
    private OnItemClickListener onItemClickListener;

    /**
     * Constructor para inicializar el adaptador con el contexto y la lista de personajes.
     *
     * @param context Contexto de la actividad o fragmento.
     * @param personajeList Lista de personajes a mostrar.
     */
    public AdapterPersonaje(Context context, List<Personaje> personajeList) {
        this.context = context;
        this.personajeList = personajeList;
    }

    /**
     * Crea un nuevo ViewHolder inflando el layout de cada elemento de la lista.
     *
     * @param parent El ViewGroup padre donde se adjuntará el nuevo ViewHolder.
     * @param viewType El tipo de vista de la nueva vista.
     * @return Un nuevo objeto PersonajeViewHolder.
     */
    @Override
    public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_personaje, parent, false);
        return new PersonajeViewHolder(view);
    }

    /**
     * Vincula los datos de un personaje al ViewHolder para mostrarlo en una posición específica.
     *
     * @param holder El ViewHolder donde se configurarán los datos.
     * @param position La posición del personaje en la lista.
     */
    @Override
    public void onBindViewHolder(PersonajeViewHolder holder, int position) {
        Personaje personaje = personajeList.get(position);
        holder.nameTextView.setText(personaje.getName());
        holder.imageView.setImageResource(personaje.getImageResource());
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Tamaño de la lista de personajes.
     */
    @Override
    public int getItemCount() {
        return personajeList.size();
    }

    /**
     * Configura un listener para manejar los clics en los elementos de la lista.
     *
     * @param listener El listener de clics para cada elemento.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * Interfaz para manejar los eventos de clic en los elementos de la lista.
     */
    public interface OnItemClickListener {
        /**
         * Método llamado cuando se hace clic en un elemento de la lista.
         *
         * @param personaje El objeto Personaje que fue clicado.
         */
        void onItemClick(Personaje personaje);
    }

    /**
     * Clase interna que representa un ViewHolder para cada elemento de la lista de personajes.
     */
    public class PersonajeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /** TextView para mostrar el nombre del personaje */
        TextView nameTextView;

        /** ImageView para mostrar la imagen del personaje */
        ImageView imageView;

        /**
         * Constructor para inicializar las vistas y configurar el listener de clics en el itemView.
         *
         * @param itemView La vista del elemento de la lista.
         */
        public PersonajeViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewPersonajeName);
            imageView = itemView.findViewById(R.id.imageViewPersonaje);

            itemView.setOnClickListener(this);
        }

        /**
         * Método llamado cuando se hace clic en el ViewHolder.
         *
         * @param v La vista que fue clicada.
         */
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(personajeList.get(getAdapterPosition()));
            }
        }
    }
}
