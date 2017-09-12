package hugheshl.easyfeelin;

import android.support.v4.app.Fragment;

public class AssignListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AssignListFragment();
    }
}
