package br.net.daumhelp.menu.resposta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RespostaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RespostaViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

}
