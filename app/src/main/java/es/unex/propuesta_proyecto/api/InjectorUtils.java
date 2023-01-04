package es.unex.propuesta_proyecto.api;

import android.content.Context;
import es.unex.propuesta_proyecto.dao.AppDataBase;

public class InjectorUtils {

    public static ArmasRepository provideRepository(Context context) {
        AppDataBase database = AppDataBase.getInstance(context.getApplicationContext());
        ArmaNetworkDataSource networkDataSource = ArmaNetworkDataSource.getInstance();
        return ArmasRepository.getInstance(database.daoJuego(), networkDataSource);
    }

    public static ArmasViewModelFactory provideMainActivityViewModelFactory(Context context) {
        ArmasRepository repository = provideRepository(context.getApplicationContext());
        return new ArmasViewModelFactory(repository);
    }

}