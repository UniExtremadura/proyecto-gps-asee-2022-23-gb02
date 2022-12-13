package es.unex.propuesta_proyecto.activities;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import android.content.Context;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.model.Usuarios;

public class LoginActivityTest {

    private Usuarios usuario;
    Context context;

    @Before
    public void setUp() {
        usuario = new Usuarios();
    }

    @Test
    public void onCreate() {
        usuario.setName("David");
        usuario.setPassword("12345");
        AppExecutors.getInstance().diskIO().execute(() -> AppDataBase.getInstance(context.getApplicationContext()).daoUsuarios().insertarUsuario(usuario));
        AppExecutors.getInstance().diskIO().execute(() -> {
            Usuarios usuarioTest = AppDataBase.getInstance(context.getApplicationContext()).daoUsuarios().comprobarUsuario("David");
            assertEquals(usuarioTest.getName(), "David");
            assertEquals(usuarioTest.getPassword(), "12345");
        });
    }

    @After
    public void tearDown() {
     AppExecutors.getInstance().diskIO().execute(() -> AppDataBase.getInstance(context.getApplicationContext()).daoUsuarios().borrarUsuario(usuario));
    }
}