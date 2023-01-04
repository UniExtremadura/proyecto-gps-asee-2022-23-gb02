package es.unex.propuesta_proyecto.api;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class ArmasViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ArmasRepository mRepository;

    public ArmasViewModelFactory(ArmasRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ArmasViewModel(mRepository);
    }
}