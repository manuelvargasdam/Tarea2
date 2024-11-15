package com.tarea2.mvs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.tarea2.mvs.databinding.FragmentHomeBinding;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Fragmento principal que muestra una lista de personajes utilizando un RecyclerView.
 */
public class HomeFragment extends Fragment {

    /** Tag para el log de esta clase */
    private static final String TAG = "HomeFragment";

    /** Enlace para acceder a las vistas del layout de este fragmento */
    private FragmentHomeBinding binding;

    /** RecyclerView para mostrar la lista de personajes */
    private RecyclerView recyclerView;

    /** Adaptador para gestionar la lista de personajes en el RecyclerView */
    private AdapterPersonaje adapterPersonaje;

    /** Lista de personajes a mostrar en el RecyclerView */
    private List<Personaje> personajeList;

    /**
     * Método que infla el layout del fragmento y configura el RecyclerView.
     *
     * @param inflater Inflador de vistas.
     * @param container Contenedor padre.
     * @param savedInstanceState Estado guardado de la instancia.
     * @return Vista raíz del fragmento.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout y configurar el enlace de datos
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        try {
            // Configuración inicial del RecyclerView
            recyclerView = binding.recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);

            // Inicialización de la lista de personajes y su configuración
            personajeList = new ArrayList<>();
            personajeList.add(new Personaje("Mario", R.drawable.mario_image, "Héroe del Reino Champiñón", "Salta alto, Lucha contra Bowser"));
            personajeList.add(new Personaje("Luigi", R.drawable.luigi_image, "Hermano de Mario", "Más alto que Mario, Lucha contra fantasmas"));
            personajeList.add(new Personaje("Peach", R.drawable.peach_image, "Princesa del Reino Champiñón", "Puede flotar en el aire, Utiliza su paraguas para atacar, Líder valiente"));
            personajeList.add(new Personaje("Toad", R.drawable.toad_image, "Guardián del Reino Champiñón", "Pequeño y rápido, Ayuda a Mario"));

            // Configuración del adaptador con la lista de personajes
            adapterPersonaje = new AdapterPersonaje(getContext(), personajeList);
            recyclerView.setAdapter(adapterPersonaje);

            // Establecer el click listener para manejar los clics en los elementos de la lista
            adapterPersonaje.setOnItemClickListener(new AdapterPersonaje.OnItemClickListener() {
                @Override
                public void onItemClick(Personaje personaje) {
                    try {
                        // Crear un Bundle para pasar los datos del personaje seleccionado
                        Bundle bundle = new Bundle();
                        bundle.putString("personaje_name", personaje.getName());
                        bundle.putInt("personaje_image", personaje.getImageResource());
                        bundle.putString("personaje_description", personaje.getDescription());
                        bundle.putString("personaje_abilities", personaje.getAbilities());

                        // Utilizar NavController para navegar al fragmento de perfil con los datos del personaje
                        NavController navController = NavHostFragment.findNavController(HomeFragment.this);
                        navController.navigate(R.id.profileFragment, bundle);
                    } catch (Exception e) {
                        Log.e(TAG, "Error al navegar al fragmento de perfil: " + e.getMessage(), e);
                    }
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "Error en onCreateView: " + e.getMessage(), e);
        }

        // Retornar la vista raíz del fragmento configurada
        return binding.getRoot();
    }
}
