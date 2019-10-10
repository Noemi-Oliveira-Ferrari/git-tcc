package br.net.daumhelp.menu.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class PedidosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PedidosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Está será a tela de pagamento!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}