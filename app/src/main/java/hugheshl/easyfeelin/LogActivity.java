package hugheshl.easyfeelin;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class LogActivity extends SingleFragmentActivity {

    public static final String EXTRA_ASSIGN_ID = "hugheshl.turniptimer2.assign_id";

    public static Intent newIntent(Context packageContext, UUID assignID) {
        Intent intent = new Intent(packageContext, LogActivity.class);
        intent.putExtra(EXTRA_ASSIGN_ID, assignID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new LogFragment();
    }
}
