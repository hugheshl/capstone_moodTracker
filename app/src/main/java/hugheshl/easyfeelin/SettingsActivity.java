package hugheshl.easyfeelin;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class SettingsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext, UUID assignID) {
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        //intent.putExtra();
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new SettingsFragment();
    }
}