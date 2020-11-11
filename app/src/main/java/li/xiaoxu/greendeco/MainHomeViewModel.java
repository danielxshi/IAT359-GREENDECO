package li.xiaoxu.greendeco.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MainHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is main home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}