package br.net.daumhelp.menuCli.perfilCli;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PerfilCliViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PerfilCliViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}