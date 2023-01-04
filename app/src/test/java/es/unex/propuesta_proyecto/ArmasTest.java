package es.unex.propuesta_proyecto;

import android.content.Context;

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
import es.unex.propuesta_proyecto.dao.DaoJuego;
import es.unex.propuesta_proyecto.model.Armas;

@RunWith(AndroidJUnit4.class)
public class ArmasTest {
    private AppDataBase armasDB;
    private DaoJuego dao_armas;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void crearArmasDB(){
        Context context = ApplicationProvider.getApplicationContext();
        armasDB = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).allowMainThreadQueries().build();
        dao_armas = armasDB.daoJuego();
    }

    @Test
    public void getArmasTest(){
        dao_armas = armasDB.daoJuego();
        Assert.assertNotNull(dao_armas);
    }

    @Test
    public void insertarArmaTest(){
        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto","Carlos",1, 1);
        dao_armas.insertarArmas(a);
        Armas aRecup = dao_armas.obtenerArmas().get(0);
        Assert.assertEquals(aRecup.getName(), a.getName());
    }

    @Test
    public void actualizarArmaTest(){
        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto","Carlos",1, 1);
        dao_armas.insertarArmas(a);
        dao_armas.actualizarArma("TestActualizar", "Weapon", "Base",64,23,12,5,87,65, 1, 1, 1);
        Armas aRecup = dao_armas.obtenerArmas().get(0);
        Assert.assertEquals(aRecup.getName(), "TestActualizar");
    }

    @Test
    public void borrarArmaTest(){
        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto","Carlos",1, 1);
        dao_armas.insertarArmas(a);
        dao_armas.borrarArma(1);
        Assert.assertEquals(dao_armas.obtenerArmas().size(), 0);
    }

    @After
    public void closeArmasDB(){
        armasDB.close();
    }
}
