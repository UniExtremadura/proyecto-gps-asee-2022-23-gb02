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
import es.unex.propuesta_proyecto.dao.AppDatabaseUsuarios;
import es.unex.propuesta_proyecto.dao.DaoJuego;
import es.unex.propuesta_proyecto.dao.DaoUsuarios;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    private AppDatabaseUsuarios loginDB;
    private DaoUsuarios dao_to_test;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void loginTestDB(){
        Context context = ApplicationProvider.getApplicationContext(); // Se obtiene el contexto de la aplicación
        loginDB = Room.inMemoryDatabaseBuilder(context,AppDatabaseUsuarios.class).allowMainThreadQueries().build();
        dao_to_test = loginDB.daoUsuarios();
    }


    @Test
    public void getTestDao(){
        dao_to_test = loginDB.daoUsuarios();
        Assert.assertNotNull(dao_to_test);
    }

    @After
    public void closeLoginTestDB(){loginDB.close();}

}
