package hugheshl.easyfeelin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class AssignListFragment extends Fragment {

    private RecyclerView mAssignRecyclerView;
    private AssignAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // ******************************* Dummy Data for Demonstration *****************************
        for(int i = 0; i < 14; i++) {
            Assign assign = new Assign();
            assign.setTitle("Entry" + i);
                assign.setCompleted(true);
            AssignLab.get(getActivity()).addAssign(assign);
        }
        // ******************************* Dummy Data for Demonstration *****************************
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assign_list, container, false);
        mAssignRecyclerView = (RecyclerView) view.findViewById(R.id.assign_recycler_view);
        mAssignRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
        }

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Remove swiped item from list and notify the RecyclerView
                        //TO_DO
                        Toast.makeText(getActivity(), "Swipe Deletion Coming Soon", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder vh1, RecyclerView.ViewHolder vh2) {
                        return false;
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mAssignRecyclerView);

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_assign_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_assign:
                Assign assign = new Assign();
                AssignLab.get(getActivity()).addAssign(assign);
                Intent intent = AssignPagerActivity.newIntent(getActivity(), assign.getId());
                startActivity(intent);
                return true;
            case R.id.settings:
                Intent intent2 = SettingsActivity.newIntent(getActivity(), null);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        AssignLab assignLab = AssignLab.get(getActivity());
        List<Assign> assigns = assignLab.getAssigns();

        if (mAdapter == null) {
            mAdapter = new AssignAdapter(assigns);
            mAssignRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setAssigns(assigns);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class AssignHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Assign mAssign;

        private TextView mTitleTextView;
        private TextView mTimeTextView;
        private TextView mDateTextView;
        private ImageView mCompletedImageView;

        public AssignHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_assign, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.assign_title);
            mTimeTextView = (TextView) itemView.findViewById(R.id.assignment_time);
            mDateTextView = (TextView) itemView.findViewById(R.id.assign_date);
            mCompletedImageView = (ImageView) itemView.findViewById(R.id.assign_completed);
        }

        public void bind(Assign assign) {
            mAssign = assign;
            mTitleTextView.setText(mAssign.getTitle());
            mTimeTextView.setText("here is text");
            mDateTextView.setText(DateFormat.format("EEEE, MMM dd, yyyy", mAssign.getDate()).toString());;
            mCompletedImageView.setVisibility(assign.isCompleted() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            Intent intent = AssignPagerActivity.newIntent(getActivity(), mAssign.getId());
            startActivity(intent);
        }
    }

    private class AssignAdapter extends RecyclerView.Adapter<AssignHolder> {

        private List<Assign> mAssigns;

        public AssignAdapter(List<Assign> assigns) {
            mAssigns = assigns;
        }

        @Override
        public AssignHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new AssignHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(AssignHolder holder, int position) {
            Assign assign = mAssigns.get(position);
            holder.bind(assign);
        }

        @Override
        public int getItemCount() {
            return mAssigns.size();
        }

        public void setAssigns(List<Assign> assigns) {
            mAssigns = assigns;
        }
    }
}
