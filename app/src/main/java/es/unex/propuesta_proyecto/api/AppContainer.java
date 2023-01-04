package es.unex.propuesta_proyecto.api;

import android.content.Context;

import es.unex.propuesta_proyecto.dao.AppDataBase;

public class AppContainer {

    private AppDataBase database;
    private ArmaNetworkDataSource networkDataSource;
    public ArmasRepository repository;
    public ArmasViewModelFactory factory;

    public AppContainer(Context context){
        database = AppDataBase.getInstance(context);
        networkDataSource = ArmaNetworkDataSource.getInstance();
        repository = ArmasRepository.getInstance(database.daoJuego(), networkDataSource);
        factory = new ArmasViewModelFactory(repository);
    }
}
