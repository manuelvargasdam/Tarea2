package com.tarea2.mvs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.tarea2.mvs.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    /** Variable para el binding del fragmento que enlaza las vistas */
    private FragmentProfileBinding binding;

    /** Variables para los elementos de la interfaz de usuario que mostrarán los detalles del personaje */
    private ImageView imageViewPersonajeDetail;
    private TextView textViewPersonajeName;
    private TextView textViewPersonajeDescription;
    private TextView textViewPersonajeAbilities;

    /** Método que se llama cuando se crea la vista del fragmento */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflar el layout del fragmento utilizando el binding generado */
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        /** Inicializar las vistas que se utilizarán en el fragmento */
        imageViewPersonajeDetail = binding.imageViewPersonajeDetail;
        textViewPersonajeName = binding.textViewPersonajeName;
        textViewPersonajeDescription = binding.textViewPersonajeDescription;
        textViewPersonajeAbilities = binding.textViewPersonajeAbilities;

        /** Obtener el Bundle con los datos pasados desde el fragmento anterior */
        Bundle bundle = getArguments();
        if (bundle != null) {
            /** Recuperar los valores del Bundle */
            String personajeName = bundle.getString("personaje_name");
            int personajeImage = bundle.getInt("personaje_image", 0); // Si no se pasa una imagen, se utiliza 0
            String personajeDescription = bundle.getString("personaje_description");
            String personajeAbilities = bundle.getString("personaje_abilities");

            /** Establecer los datos obtenidos en las vistas */
            imageViewPersonajeDetail.setImageResource(personajeImage); // Mostrar la imagen del personaje
            textViewPersonajeName.setText(personajeName); // Mostrar el nombre del personaje
            textViewPersonajeDescription.setText(personajeDescription); // Mostrar la descripción
            textViewPersonajeAbilities.setText(personajeAbilities); // Mostrar las habilidades

            /** Llamar al método para mostrar un mensaje cuando se selecciona un personaje */
            mostrarMensajeSeleccion(personajeName);
        } else {
            /** Si no se pasaron los datos correctamente, mostrar un mensaje de error */
            Toast.makeText(getActivity(), "No se pudieron cargar los detalles del personaje", Toast.LENGTH_SHORT).show();
        }

        /** Retornar la vista del fragmento (el root del binding) */
        return binding.getRoot();
    }

    /** Método que muestra un mensaje Toast cuando un personaje es seleccionado */
    private void mostrarMensajeSeleccion(String personajeName) {
        /** Concatenar el mensaje de selección con el nombre del personaje */
        String mensaje = getString(R.string.seleccion_personaje) + " " + personajeName;
        /** Mostrar el mensaje con un Toast */
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

}
