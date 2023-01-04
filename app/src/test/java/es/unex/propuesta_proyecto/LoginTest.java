package es.unex.propuesta_proyecto;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.dao.DaoUsuarios;
import es.unex.propuesta_proyecto.model.Usuarios;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    private AppDataBase loginDB;
    private DaoUsuarios dao_to_test;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void loginTestDB(){
        Context context = ApplicationProvider.getApplicationContext(); // Se obtiene el contexto de la aplicación
        loginDB = Room.inMemoryDatabaseBuilder(context,AppDataBase.class).allowMainThreadQueries().build();
        dao_to_test = loginDB.daoUsuarios();
    }


    @Test
    public void getTestDao(){
        dao_to_test = loginDB.daoUsuarios();
        Assert.assertNotNull(dao_to_test);
    }

    @Test
    public void insertarUsuTest(){
        Usuarios user = new Usuarios("Carlos", "1234");
        dao_to_test.insertarUsuario(user);
        Usuarios recup = dao_to_test.obtenerUsuarios().get(0);
        Assert.assertEquals(recup.getName(), "Carlos");
        Assert.assertEquals(recup.getPassword(), "1234");
    }

    @Test
    public void actualizarUsuTest(){
        Usuarios user = new Usuarios("Carlos", "1234");
        dao_to_test.insertarUsuario(user);
        dao_to_test.actualizarContrasena("Carlos", "Prueba1");
        Usuarios recup = dao_to_test.obtenerUsuarios().get(0);
        Assert.assertEquals(recup.getPassword(), "Prueba1");
    }

    @Test
    public void borrarUsuTest(){
        Usuarios user = new Usuarios("Carlos", "1234");
        dao_to_test.insertarUsuario(user);
        dao_to_test.borrarUsuario(user);
        Assert.assertEquals(dao_to_test.obtenerUsuarios().size(), 0);
    }

    @After
    public void closeLoginTestDB(){loginDB.close();}

}
