package hugheshl.easyfeelin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class AssignPagerActivity extends AppCompatActivity {

    private static final String EXTRA_ASSIGN_ID = "hugheshl.turniptimer2.assign_id";

    private ViewPager mViewPager;
    private List<Assign> mAssigns;

    public static Intent newIntent(Context packageContext, UUID assignId) {
        Intent intent = new Intent(packageContext, AssignPagerActivity.class);
        intent.putExtra(EXTRA_ASSIGN_ID, assignId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_pager);

        UUID assignId = (UUID) getIntent().getSerializableExtra(EXTRA_ASSIGN_ID);

        mViewPager = (ViewPager) findViewById(R.id.assign_view_pager);

        mAssigns = AssignLab.get(this).getAssigns();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Assign assign = mAssigns.get(position);
                return AssignFragment.newInstance(assign.getId());
            }

            @Override
            public int getCount() {
                return mAssigns.size();
            }
        });

        for (int i = 0; i < mAssigns.size(); i++) {
            if (mAssigns.get(i).getId().equals(assignId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
