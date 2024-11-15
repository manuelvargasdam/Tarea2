package com.tarea2.mvs;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.tarea2.mvs.databinding.ActivityMainBinding;

import java.util.Locale;

/**
 * Actividad principal que gestiona la navegación y configuración general de la app.
 */
public class MainActivity extends AppCompatActivity {

    /** Tag para el log de esta clase */
    private static final String TAG = "MainActivity";

    /** Toggle para abrir/cerrar el menú lateral */
    private ActionBarDrawerToggle toggle;

    /** Enlace a las vistas del layout de la actividad */
    private ActivityMainBinding binding;

    /** Controlador de navegación para gestionar los fragmentos */
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Instala la pantalla de inicio (SplashScreen) al abrir la app
        // Configurar el idioma de la app al inicio según las preferencias
        setLanguage(isSpanish());

        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);

        try {
            // Configura el enlace y establece la vista raíz
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            // Obtiene el NavController desde el NavHostFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
            navController = navHostFragment.getNavController();

            // Configura el toggle del menú lateral
            configureToggleMenu();

            // Configura la navegación en el menú lateral
            configureNavigation();

            // Muestra el icono de menú en la ActionBar
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error en onCreate: " + e.getMessage(), e);
        }
    }

    /**
     * Configura el ActionBarDrawerToggle para el menú lateral
     */
    private void configureToggleMenu() {
        try {
            // Configura el ActionBarDrawerToggle para abrir/cerrar el menú lateral
            toggle = new ActionBarDrawerToggle(
                    this,
                    binding.drawerLayout,
                    R.string.open_drawer,
                    R.string.close_drawer
            );
            binding.drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        } catch (Exception e) {
            Log.e(TAG, "Error al configurar el menú toggle: " + e.getMessage(), e);
        }
    }

    /**
     * Configura la navegación con el NavController y la selección de elementos del menú.
     */
    private void configureNavigation() {
        try {
            // Configura la navegación del menú lateral con el NavController
            NavigationUI.setupWithNavController(binding.navView, navController);

            // Maneja la selección de elementos del menú
            binding.navView.setNavigationItemSelectedListener(menuItem -> {
                if (menuItem.getItemId() == R.id.nav_home) {
                    navController.navigate(R.id.homeFragment); // Navega al fragmento de inicio
                } else if (menuItem.getItemId() == R.id.switch_language) {
                    mostrarDialogoIdioma(); // Muestra el diálogo para cambiar el idioma
                }
                binding.drawerLayout.closeDrawers(); // Cierra el menú después de seleccionar
                return true;
            });
        } catch (Exception e) {
            Log.e(TAG, "Error al configurar la navegación: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            // Infla el menú para agregar opciones en la ActionBar
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menuacercade, menu);
        } catch (Exception e) {
            Log.e(TAG, "Error al inflar el menú: " + e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            // Maneja la selección del toggle del menú
            if (toggle.onOptionsItemSelected(item)) {
                return true;
            }

            // Maneja la selección de la opción "Acerca de" en el menú
            int id = item.getItemId();
            if (id == R.id.menu_acerca_de) {
                mostrarDialogoAcercaDe();
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en onOptionsItemSelected: " + e.getMessage(), e);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        try {
            // Maneja la navegación al fragmento de origen cuando se presiona "Back"
            Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            if (navHostFragment != null) {
                NavController navController = NavHostFragment.findNavController(navHostFragment);
                return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en onSupportNavigateUp: " + e.getMessage(), e);
        }
        return super.onSupportNavigateUp();
    }

    /**
     * Muestra el diálogo "Acerca de" para informar sobre la app.
     */
    private void mostrarDialogoAcercaDe() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.menu_acerca_de)
                    .setMessage(R.string.acerca_de_message)
                    .setPositiveButton(R.string.cerrar, (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, "Error al mostrar el diálogo 'Acerca de': " + e.getMessage(), e);
        }
    }

    /**
     * Muestra un diálogo para cambiar el idioma de la aplicación entre español e inglés.
     */
    private void mostrarDialogoIdioma() {
        try {
            boolean isSpanish = isSpanish();

            // Crear un Switch para seleccionar el idioma
            Switch idiomaSwitch = new Switch(this);
            idiomaSwitch.setChecked(isSpanish);

            // Crear el diálogo para seleccionar el idioma
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Español")
                    .setView(idiomaSwitch)
                    .setPositiveButton("Aceptar", (dialog, id) -> {
                        changeLanguage(idiomaSwitch.isChecked());
                    })
                    .setNegativeButton("Cancelar", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, "Error al mostrar el diálogo de idioma: " + e.getMessage(), e);
        }
    }

    /**
     * Cambia el idioma de la aplicación y guarda la preferencia seleccionada.
     *
     * @param isSpanish True para español, false para inglés.
     */
    private void changeLanguage(boolean isSpanish) {
        try {
            SharedPreferences preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isSpanish", isSpanish);
            editor.apply();

            // Cambia el idioma de la aplicación
            setLanguage(isSpanish);

            // Reinicia la actividad para aplicar el cambio de idioma
            recreate();
        } catch (Exception e) {
            Log.e(TAG, "Error al cambiar el idioma: " + e.getMessage(), e);
        }
    }

    /**
     * Verifica si el idioma actual de la app es español.
     *
     * @return True si es español, de lo contrario false.
     */
    private boolean isSpanish() {
        SharedPreferences preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
        return preferences.getBoolean("isSpanish", true); // Por defecto en español
    }

    /**
     * Configura el idioma de la app según la preferencia seleccionada.
     *
     * @param isSpanish True para español, false para inglés.
     */
    private void setLanguage(boolean isSpanish) {
        try {
            Locale locale = isSpanish ? new Locale("es") : new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        } catch (Exception e) {
            Log.e(TAG, "Error al configurar el idioma: " + e.getMessage(), e);
        }
    }
}
