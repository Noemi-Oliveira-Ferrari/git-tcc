package br.net.daumhelp.menuCli.homeCli;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class BuscaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BuscaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Está será a tela de pagamento!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}